package com.fmk.framework.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import javax.servlet.http.HttpServletRequest;

@Component
@SummerService
public class GetRequestHeaderService implements SummerServiceBean<GetRequestHeader> {
    @Override
    public void sum(GetRequestHeader summer) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String headerValue = request.getHeader(summer.getName());

            summer.setSummerResult(headerValue);
        }catch (IllegalStateException e){
            summer.setSummerResult("");
        }
    }
}
