package com.fmk.framework.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import javax.servlet.http.HttpServletResponse;

@Component
@SummerService
public class SetResponseHeaderService implements SummerServiceBean<SetResponseHeader> {
    @Override
    public void sum(SetResponseHeader summer) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletResponse response = requestAttributes.getResponse();

        if(response.containsHeader(summer.getName())){
            return;
        }

        response.addHeader(summer.getName(), summer.getValue());
    }
}
