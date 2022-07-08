package com.fmk.framework.dao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jerry
 */
@Component
@Aspect
public class SimpleTransactionIdInterceptor {

    @Autowired
    private Dao dao;

    @Around("@within(com.fmk.framework.daoannotations.SimpleTransactionId)||@annotation(com.fmk.framework.daoannotations.SimpleTransactionId)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        dao.newSimpleTransactionId();
        try {
            return point.proceed();
        }finally {
            dao.removeSimpleTransactionId();
        }
    }
}
