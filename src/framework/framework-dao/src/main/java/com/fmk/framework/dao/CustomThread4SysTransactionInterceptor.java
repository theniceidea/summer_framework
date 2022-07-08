package com.fmk.framework.dao;

import com.fmk.framework.daomodel.DaoCommitCurrentTransaction;
import com.fmk.framework.daomodel.DaoRollbackCurrentTransactionRes;
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
public class CustomThread4SysTransactionInterceptor {

    @Autowired
    private Dao dao;

    @Around("@within(com.fmk.framework.daoannotations.SysTransaction)||@within(com.fmk.framework.web.SysRequestTransactionInit)||@annotation(com.fmk.framework.daoannotations.SysTransaction)||@annotation(com.fmk.framework.web.SysRequestTransactionInit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        dao.newTransaction();
        try{
            Object result = point.proceed();
            DaoCommitCurrentTransaction.s();
            return result;
        }catch (Throwable e){
            DaoRollbackCurrentTransactionRes.s();
            throw e;
        }
    }
}
