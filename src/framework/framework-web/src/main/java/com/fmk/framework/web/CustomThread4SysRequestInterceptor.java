package com.fmk.framework.web;

import cn.hutool.core.lang.ObjectId;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jerry
 */
@Component
@Aspect
public class CustomThread4SysRequestInterceptor {

    @Value("${app.deploy.normalVersion}")
    private String normalVersion;

    @Around("@within(com.fmk.framework.web.SysRequest)||@within(com.fmk.framework.web.SysRequestTransactionInit)||@annotation(com.fmk.framework.web.SysRequest)||@annotation(com.fmk.framework.web.SysRequestTransactionInit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        String requestId = ObjectId.next();
        SetCurrentRequestId.s(requestId);
        SetRequestDeployVersion.s(normalVersion);

        return point.proceed();
    }
}
