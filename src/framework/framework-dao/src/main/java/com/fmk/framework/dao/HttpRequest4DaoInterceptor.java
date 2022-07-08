package com.fmk.framework.dao;

import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.daomodel.DaoCommitCurrentTransaction;
import com.fmk.framework.daomodel.DaoRollbackCurrentTransactionRes;
import com.fmk.framework.web.IsMicroServiceCall;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.summerframework.model.SummerService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author jerry
 */
@Component
@SummerService
@Aspect
public class HttpRequest4DaoInterceptor {

    @Autowired
    private Dao dao;

    @SuppressWarnings("")
    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)||@within(org.springframework.web.bind.annotation.GetMapping)||@within(org.springframework.web.bind.annotation.PostMapping)||@within(org.springframework.web.bind.annotation.PutMapping)||@within(org.springframework.web.bind.annotation.DeleteMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if(!ClassUtil.isAppClass(method.getDeclaringClass())) { return point.proceed(); }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Class<? extends Annotation> feignClientKls = (Class<? extends Annotation>) ReflectUtil.getClassNoThrow(Consts.FEIGN_CLIENT_CLASS_NAME);
        if(null != feignClientKls) {
            Annotation feignAnnotation = method.getDeclaringClass()
                .getAnnotation(feignClientKls);
            if(null != feignAnnotation) { return point.proceed(); }
        }
        boolean isMicroServiceCall = IsMicroServiceCall.s();
        HttpServletRequest request = requestAttributes.getRequest();

        if(isMicroServiceCall) {
            String transactionId = request.getHeader(Consts.SERVICE_TRANSACTION_ID);
            dao.transId(transactionId);

            return point.proceed();
        }else{
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
}
