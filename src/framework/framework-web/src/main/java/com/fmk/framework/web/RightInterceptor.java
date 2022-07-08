package com.fmk.framework.web;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

//@Component
//@Aspect
public class RightInterceptor {

    @Around("@within(com.fmk.framework.cms.framework.annotations.Right)||@annotation(com.fmk.framework.cms.framework.annotations.Right)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

//        String className = point.getTarget().getClass().getSimpleName();
//        Method method = ((MethodSignature) point.getSignature()).getMethod();
//        if(!ClassUtil.isAppClass(method.getDeclaringClass())) { return point.proceed(); }
//
//        String rightId = className+":"+method.getName();
//
//        boolean hasRight = HasRightIdSum.sum(rightId);
//        if (!hasRight) throw Excep.le(this.getClass(), ErrMsgs.NotHasRight);

        return point.proceed();
    }
}
