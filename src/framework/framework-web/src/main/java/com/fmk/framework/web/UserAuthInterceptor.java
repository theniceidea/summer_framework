package com.fmk.framework.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fmk.framework.annotations.PreAuthorize;
import com.fmk.framework.annotations.SkipUserAuth;
import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ThreadLocals;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.session.CurrentSessionId;
import com.fmk.framework.session.CurrentUserId;
import com.fmk.framework.session.CurrentUserInfo;
import com.fmk.framework.session.SetCurrentUserId;
import com.fmk.framework.validation.Precondition;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.summerframework.model.SummerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

@SummerService
@Aspect
public class UserAuthInterceptor {
    private static Logger logger = Logger.getLogger(UserAuthInterceptor.class);
    public static final String STR_AUTHORIZATION = "Authorization";
    public static final String STR_BEARER = "Bearer";
    public static final String STR_EXP = "exp";
    private static final int SESSION_LIFE_TIME = 3;

    private static final ThreadLocal<String> CURRENT_USER_ID = ThreadLocals.newThreadLocal();


//    @Value("${app.session-key}")
//    private String sessionKey;

//    @Value("${app.current-user-id-key}")
//    private String currentUserIdKey;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtService jwtService;

    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)||@within(org.springframework.web.bind.annotation.GetMapping)||@within(org.springframework.web.bind.annotation.PostMapping)||@within(org.springframework.web.bind.annotation.PutMapping)||@within(org.springframework.web.bind.annotation.DeleteMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if (!ClassUtil.isAppClass(method.getDeclaringClass())) {
            return point.proceed();
        }
        Class<? extends Annotation> feignClientKls = (Class<? extends Annotation>) ReflectUtil.getClassNoThrow(Consts.FEIGN_CLIENT_CLASS_NAME);
        if (null != feignClientKls) {
            Annotation feignAnnotation = method.getDeclaringClass()
                    .getAnnotation(feignClientKls);
            if (null != feignAnnotation) {
                return point.proceed();
            }
        }

        if (IsMicroServiceCall.s()) {
            return point.proceed();
        }

        SkipUserAuth skipUserAuth = method.getAnnotation(SkipUserAuth.class);
        if (null == skipUserAuth) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpServletResponse response = requestAttributes.getResponse();
            String header = request.getHeader(STR_AUTHORIZATION);

            String sessionValue = (null != header && header.startsWith(STR_BEARER + " ")) ? header.substring(7).trim() : "";
            String currentUserId = null;

//        Cookie[] cookies = request.getCookies();
//        String sessionValue = null;
//        if(null != cookies){
//            for(Cookie cookie : cookies){
//                if(Objects.equals(Consts.SESSION_ID_COOKIE_KEY, cookie.getName())){
//                    sessionValue = cookie.getValue();
//                }
//            }
//        }
            if (StringUtils.isNotBlank(sessionValue)) {
                Map<String, Object> decode = jwtService.decodeAndVerify(sessionValue);
//            currentUserId = redisTemplate.opsForValue().get(Consts.REDIS_SESSION_KEY + sessionValue);
                String requestId = CurrentRequestId.s(false);
                logger.info("urlrequest-in:jwt {} {}", requestId, JSON.toJSONString(decode));
                currentUserId = (String) decode.get(Consts.USER_PROPERTY_ID);

                if (StringUtils.isNotBlank(currentUserId)) {
                    CheckJwtToken.s(sessionValue,currentUserId);
//                redisTemplate.opsForValue().set(Consts.REDIS_CURRENT_USER+currentUserId, JSON.toJSONString(decode), 20, TimeUnit.MINUTES);
                    SetCurrentUserInfo.s(currentUserId, decode.containsKey(Consts.USER_PROPERTY_ADMIN));
//                redisTemplate.expire(Consts.REDIS_CURRENT_USER+currentUserId, 1, TimeUnit.DAYS);

                    String requestURI = request.getRequestURI();
                    if (!decode.containsKey(Consts.USER_PROPERTY_ADMIN)) {
                        Precondition.checkState(requestURI.contains(Consts.REQUEST_URL_PREFIX_PORTAL), ErrMsgs.Err_000200000016);
                        CheckUserIsAgree.s(request.getRequestURI(), currentUserId);
                    } else {
                        Precondition.checkState(requestURI.contains(Consts.REQUEST_URL_PREFIX_CMS), ErrMsgs.Err_000200000016);
                    }
//                    requestAttributes.setAttribute(Consts.REQUEST_CURRENT_USER_ID_KEY, currentUserId, RequestAttributes.SCOPE_REQUEST);
                    CURRENT_USER_ID.set(currentUserId);
//                    requestAttributes.setAttribute(Consts.REQUEST_CURRENT_SESSION_ID, currentUserId, RequestAttributes.SCOPE_REQUEST);

                    decode.put(STR_EXP, DateTime.now().plusDays(SESSION_LIFE_TIME).getMillis() / 1000);
                    String encode = jwtService.encode(JSON.toJSONString(decode));
                    response.setHeader(STR_AUTHORIZATION, STR_BEARER + " " + encode);
                }
            }

            Precondition.checkState(StringUtils.isNotBlank(currentUserId), this.getClass(), ErrMsgs.Err_000000000010);

            PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
            if (null != preAuthorize) {
                String authority = preAuthorize.value();
                CheckUserRight.s(authority, currentUserId);
            }
        }

        return point.proceed();
    }

    public void currentUserId(CurrentUserId model) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        String value = (String) requestAttributes.getAttribute(Consts.REQUEST_CURRENT_USER_ID_KEY, RequestAttributes.SCOPE_REQUEST);
        String value = CURRENT_USER_ID.get();

        boolean bolThrow = model.isNotFoundThrow() && StringUtils.isEmpty(value);

        Precondition.checkState(!bolThrow, this.getClass(), ErrMsgs.Err_000000000010);

        model.setSummerResult(value);
    }

    public void setCurrentUserId(SetCurrentUserId model) {

        CURRENT_USER_ID.set(model.getUserId());
    }

    public void currentSessionId(CurrentSessionId model) {
        String sessionId = CurrentUserId.s(true);
        model.setSummerResult(sessionId);
    }

    public void currentUserInfo(CurrentUserInfo model) {
        String userId = CurrentUserId.s(true);
        String json = redisTemplate.opsForValue().get(Consts.REDIS_CURRENT_USER + userId);

        Precondition.checkState(StringUtils.isNotBlank(json), this.getClass(), ErrMsgs.Err_000000000010);

        JSONObject jsonObject = JSON.parseObject(json);
        model.setSummerResult(jsonObject);
    }
}
