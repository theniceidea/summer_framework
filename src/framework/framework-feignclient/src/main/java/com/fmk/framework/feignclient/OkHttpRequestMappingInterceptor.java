package com.fmk.framework.feignclient;

import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.Excep;
import jodd.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Component
@Aspect
public class OkHttpRequestMappingInterceptor {


    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)||@within(org.springframework.web.bind.annotation.GetMapping)||@within(org.springframework.web.bind.annotation.PostMapping)||@within(org.springframework.web.bind.annotation.PutMapping)||@within(org.springframework.web.bind.annotation.DeleteMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if(!ClassUtil.isAppClass(method.getDeclaringClass())) { return point.proceed(); }
        FeignClient annotation = method.getDeclaringClass()
            .getAnnotation(FeignClient.class);

        if(null == annotation) { return point.proceed(); }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        try {
            Object proceed = point.proceed();

            String code = (String) requestAttributes.getAttribute(Consts.SERVICE_RESULT_CODE, RequestAttributes.SCOPE_REQUEST);
            String msg = (String) requestAttributes.getAttribute(Consts.SERVICE_RESULT_MSG, RequestAttributes.SCOPE_REQUEST);

            if (StringUtil.isNotBlank(code)) {
                throw Excep.le(this.getClass(), code, msg);
            }
            return proceed;
        }finally {
            requestAttributes.removeAttribute(Consts.SERVICE_RESULT_CODE, RequestAttributes.SCOPE_REQUEST);
            requestAttributes.removeAttribute(Consts.SERVICE_RESULT_MSG, RequestAttributes.SCOPE_REQUEST);
        }
    }
}
