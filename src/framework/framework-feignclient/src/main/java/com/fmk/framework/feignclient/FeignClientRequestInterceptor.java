package com.fmk.framework.feignclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.web.CurrentRequestId;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Component
@Aspect
public class FeignClientRequestInterceptor {
    private static Logger logger = Logger.getLogger("requestLog");


    @Around("@within(org.springframework.web.bind.annotation.RequestMapping)||@within(org.springframework.web.bind.annotation.GetMapping)||@within(org.springframework.web.bind.annotation.PostMapping)||@within(org.springframework.web.bind.annotation.PutMapping)||@within(org.springframework.web.bind.annotation.DeleteMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if(!ClassUtil.isAppClass(method.getDeclaringClass())) { return point.proceed(); }
        FeignClient annotation = method.getDeclaringClass()
            .getAnnotation(FeignClient.class);

        if(null == annotation) { return point.proceed(); }

        Object[] args = point.getArgs();
        if(args.length<=0){
            throw new ApplicationException("feign client 参数错误");
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        String requestId = (String) requestAttributes.getAttribute(Consts.SERVICE_REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
        String requestId = CurrentRequestId.s(true);

        Object arg = args[0];
        String inParam = JSON.toJSONString(arg);
        String klsName = arg.getClass()
            .getName();
        logger.info("in {} {}: {}", klsName, requestId, inParam);
        Object proceed = point.proceed();

        String outParam = JSON.toJSONString(proceed);
        logger.info("out {} {}: {}", klsName, requestId, outParam);

        return proceed;
    }
}
