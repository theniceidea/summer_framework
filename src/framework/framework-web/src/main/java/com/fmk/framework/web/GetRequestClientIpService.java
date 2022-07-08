//package com.fmk.framework.web;
//
//import cn.hutool.http.HttpUtil;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.summerframework.model.SummerService;
//import org.summerframework.model.SummerServiceBean;
//
//@Service
//@SummerService
//public class GetRequestClientIpService implements SummerServiceBean<GetRequestClientIp> {
//    @Override
//    public void sum(GetRequestClientIp summer) {
//
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        String clientIP = HttpUtil.getClientIP(requestAttributes.getRequest());
//
//        summer.setSummerResult(clientIP);
//    }
//}
