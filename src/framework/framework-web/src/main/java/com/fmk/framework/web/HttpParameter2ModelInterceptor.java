package com.fmk.framework.web;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.fmk.framework.annotations.HttpHeader;
import com.fmk.framework.annotations.SkipResultWrap;
import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ThreadLocals;
import com.fmk.framework.basic.Val;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.session.SetCurrentUserId;
import com.fmk.framework.validation.Precondition;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.val;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.summerframework.model.SummerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Component
@SummerService
@Aspect
public class HttpParameter2ModelInterceptor {
    private static Logger logger = Logger.getLogger(HttpParameter2ModelInterceptor.class);
    public static final String STR_AUTHORIZATION = "Authorization";

    private static final ThreadLocal<String> CURRENT_REQUEST_ID = ThreadLocals.newThreadLocal();
    private static final ThreadLocal<Integer> CURRENT_REQUEST_NUMBER = ThreadLocals.newThreadLocal();
    private static final ThreadLocal<String> REQUEST_REQUEST_VERSION = ThreadLocals.newThreadLocal();

//    @Value("${app.current-user-id-key}")
//    private String currentUserIdKey;

    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)||@within(org.springframework.web.bind.annotation.GetMapping)||@within(org.springframework.web.bind.annotation.PostMapping)||@within(org.springframework.web.bind.annotation.PutMapping)||@within(org.springframework.web.bind.annotation.DeleteMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if(!ClassUtil.isAppClass(method.getDeclaringClass())) { return point.proceed(); }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        logger.info("=====================================header");
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            Enumeration<String> headers = request.getHeaders(headerName);
//            logger.info("headerName:"+headerName);
//            while (headers.hasMoreElements()){
//                String headerValue = headers.nextElement();
//                logger.info("\t"+headerValue);
//            }
//        }

        Class<? extends Annotation> feignClientKls = (Class<? extends Annotation>) ReflectUtil.getClassNoThrow(Consts.FEIGN_CLIENT_CLASS_NAME);
        if(null != feignClientKls) {
            Annotation feignAnnotation = method.getDeclaringClass()
                .getAnnotation(feignClientKls);
            if(null != feignAnnotation) { return point.proceed(); }
        }
        boolean isMicroServiceCall = IsMicroServiceCall.s();

        String requestVersion = request.getHeader(Consts.REQUEST_REQUEST_VERSION);
        REQUEST_REQUEST_VERSION.set(requestVersion);
        String requestNumber = request.getHeader(Consts.SERVICE_REQUEST_NUMBER);
        if(StringUtils.isBlank(requestNumber)){
            requestNumber = "0";
        }
        CURRENT_REQUEST_NUMBER.set(Integer.valueOf(requestNumber));


//        logger.info("===============0000000000000000000:"+isMicroServiceCall);
        String requestId = "";
        if(isMicroServiceCall){
            String currentUserId = request.getHeader(Consts.SERVICE_USER_ID);
//            requestAttributes.setAttribute(Consts.REQUEST_CURRENT_USER_ID_KEY, currentUserId, RequestAttributes.SCOPE_REQUEST);
            if(StringUtils.isNotBlank(currentUserId)) {
                SetCurrentUserId.s(currentUserId);
            }

            requestId = request.getHeader(Consts.SERVICE_REQUEST_ID);
            CURRENT_REQUEST_ID.set(requestId);
//            requestAttributes.setAttribute(Consts.SERVICE_REQUEST_ID, requestId, RequestAttributes.SCOPE_REQUEST);
        }else{
            requestId = ObjectId.next();
//            requestAttributes.setAttribute(Consts.SERVICE_REQUEST_ID, requestId, RequestAttributes.SCOPE_REQUEST);
            CURRENT_REQUEST_ID.set(requestId);

            SkipResultWrap skipResultWrap = method.getAnnotation(SkipResultWrap.class);
            if(null != skipResultWrap) {
                requestAttributes.setAttribute(Consts.REQUEST_SKIP_RESULT_WRAP, "true", RequestAttributes.SCOPE_REQUEST);
            }
        }

        Annotation annotation = method.getAnnotation(GetMapping.class);
        if(null == annotation){
            annotation = method.getAnnotation(DeleteMapping.class);
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length<=0) { return point.proceed(); }

        Object[] args = point.getArgs();
        Class<?> aClass = parameterTypes[0];

        Object bean = args[0];
        if(null != annotation) {
            String msrb = request.getParameter("msrb2");
            if(StringUtils.isNotBlank(msrb)){
                msrb = URLUtil.decode(msrb);
            }else{
                msrb = request.getParameter("msrb");
            }
            if(isMicroServiceCall && (annotation instanceof GetMapping) && StringUtils.isNotBlank(msrb)) {
                bean = JSON.parseObject(msrb, aClass);
            }else {
                if (null == bean) {
                    bean = aClass.newInstance();
                }

                Enumeration<String> names = request.getParameterNames();
                while (names.hasMoreElements()) {
                    String key = names.nextElement();
                    String value = request.getParameter(key);
                    writeBeanValue(bean, key, value);
                }
            }
        }else if(null == bean){
            bean = aClass.newInstance();
        }
        args[0] = bean;

        if(isMicroServiceCall) {
//            Precondition.validation(bean);
            return point.proceed(args);
        }
        String authHeader = request.getHeader(STR_AUTHORIZATION);

        if(!(bean instanceof HaveHttpHeader)) {
//            Precondition.validation(bean);
            logger.info("urlrequest-in:{} {} {} {} {}", requestId, authHeader, request.getMethod(), request.getRequestURL(), JSON.toJSONString(bean));
            return proceout(requestAttributes, point.proceed(args));
        }

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(aClass, HttpHeader.class);
        if(CollectionUtils.isEmpty(fields)) {
//            Precondition.validation(bean);
            logger.info("urlrequest-in:{} {} {} {} {}", requestId, authHeader, request.getMethod(), request.getRequestURL(), JSON.toJSONString(bean));
            return proceout(requestAttributes, point.proceed(args));
        }

        Cookie[] cookies = request.getCookies();
        cookies = (null == cookies)?(new Cookie[]{}):cookies;
        Map<String, String> cookieMap = Lists.newArrayList(cookies)
                .stream()
                .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
        Object finalBean = bean;
        fields.forEach(field -> {
            HttpHeader httpHeader = field.getAnnotation(HttpHeader.class);
            if (httpHeader.cookie()) {
                String cookieValue = cookieMap.get(httpHeader.name());
                writeBeanValue(finalBean, field.getName(), cookieValue);
            }else{
                String headerValue = request.getHeader(httpHeader.name());
                writeBeanValue(finalBean, field.getName(), headerValue);
            }
        });

//        Precondition.validation(bean);
        logger.info("urlrequest-in:{} {} {} {} {}", requestId, authHeader, request.getMethod(), request.getRequestURL(), JSON.toJSONString(bean));
        return proceout(requestAttributes, point.proceed(args));
    }
    private Object proceout(ServletRequestAttributes requestAttributes, Object result) throws IOException {
        if(null == result) { return result; }
        if(result instanceof HaveHttpHeader){
            Class<?> aClass = result.getClass();
            ImmutableList<Field> fields = ReflectUtil.getAnnotationField(aClass, HttpHeader.class);
            if(CollectionUtils.isEmpty(fields)) { return result; }

            HttpServletResponse response = requestAttributes.getResponse();
            assert response != null;

            Val<String> location = Val.NewVal("");
            fields.forEach(field -> {
                HttpHeader httpHeader = field.getAnnotation(HttpHeader.class);
                String value = Objects.toString(ReflectUtil.fieldValueNoThrow(field, result));
                if (httpHeader.cookie()) {
                    Cookie cookie = new Cookie(httpHeader.name(), value);
                    cookie.setHttpOnly(true);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }else{
                    if(Objects.equals("Location", httpHeader.name())){
                        location.setValue(value);
                    }else {
                        response.addHeader(httpHeader.name(), value);
                    }
                }
            });
            if(StringUtils.isNotBlank(location.getValue())) {
                response.sendRedirect(location.getValue());
            }
        }
        return result;
    }
    private void writeBeanValue(Object model, String key, String value){
        try {
            Field field = ReflectUtil.getField(model.getClass(), key, false, false);
            if(null == field) { return; }
            if(StringUtils.isNotBlank(value)) {
                final Type type1 = TypeUtil.getType(field);
                if(type1.getTypeName().equals("java.util.List<java.lang.Integer>")){
                    final List<Integer> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().map(it -> Integer.valueOf(it)).collect(Collectors.toList());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.Set<java.lang.Integer>")){
                    final Set<Integer> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().map(it -> Integer.valueOf(it)).collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.HashSet<java.lang.Integer>")){
                    final HashSet<Integer> list = (HashSet<Integer>)Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().map(it -> Integer.valueOf(it)).collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.List<java.lang.String>")){
                    final List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toList());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.Set<java.lang.String>")){
                    final Set<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.HashSet<java.lang.String>")){
                    final HashSet<String> list = (HashSet<String>)Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.List<java.lang.Object>")){
                    final List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toList());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.Set<java.lang.Object>")){
                    final Set<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else if(type1.getTypeName().equals("java.util.HashSet<java.lang.Object>")){
                    final HashSet<String> list = (HashSet<String>)Splitter.on(",").omitEmptyStrings().trimResults().splitToList(value).stream().collect(Collectors.toSet());
                    BeanUtils.setProperty(model, key, list);
                }else {
                    BeanUtils.setProperty(model, key, value);
                }
                return;
            }
            Class<?> type = field.getType();
            if(!Number.class.isAssignableFrom(type) && !Boolean.class.isAssignableFrom(type)){
                BeanUtils.setProperty(model, key, value);
                return;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            //这里不log了
        }
    }
    public void currentRequestId(CurrentRequestId model) {
        String value = CURRENT_REQUEST_ID.get();

        boolean bolThrow = model.isNotFoundThrow() && StringUtils.isEmpty(value);

        Precondition.checkState(!bolThrow, this.getClass(), ErrMsgs.Err_000000000021);

        model.setSummerResult(value);
    }
    public void setCurrentRequestId(SetCurrentRequestId model) {

        CURRENT_REQUEST_ID.set(model.getRequestId());
    }
    public void requestDeployVersion(RequestDeployVersion model) {
        String result = REQUEST_REQUEST_VERSION.get();
        model.setSummerResult(result);
    }
    public void setRequestDeployVersion(SetRequestDeployVersion model) {
        REQUEST_REQUEST_VERSION.set(model.getRequestVersion());
    }

    public void setCurrentRequestNumber(SetCurrentRequestNumber model) {
        CURRENT_REQUEST_NUMBER.set(model.getRequestNumber());
    }

    public void getCurrentRequestNumber(GetCurrentRequestNumber model) {
        Integer ret = CURRENT_REQUEST_NUMBER.get();
        if(null == ret){
            ret = 0;
            CURRENT_REQUEST_NUMBER.set(ret);
        }
        model.setSummerResult(ret);
    }
}
