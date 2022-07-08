package com.fmk.framework.web;

import com.fmk.framework.web.restful.SetCrossDomainHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@SummerService
public class CrossDomainService implements SummerServiceBean<SetCrossDomainHeader> {

    @Value("${app.crossdomain.enabled:false}")
    private boolean crossDomainEnabled;

    @Value("${app.crossdomain.allow.origin:*}")
    private String crossDomainAllowOrigin;

    public void sum(SetCrossDomainHeader model){
        if(!crossDomainEnabled){ return; }
        HttpServletRequest req = (HttpServletRequest)model.getRequest();
        HttpServletResponse res = (HttpServletResponse)model.getResponse();

        res.setHeader("Access-Control-Allow-Origin", crossDomainAllowOrigin);
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");
    }
}
