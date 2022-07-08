package com.fmk.framework.restful;

import com.fmk.framework.basic.ThreadLocals;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.restful.SetCrossDomainHeader;
import com.fmk.framework.web.SetMicroServiceCall;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@Component
public class AppFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
        ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
    ) throws IOException, ServletException {
        ThreadLocals.init();

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String microServiceCallValue = request.getHeader(Consts.SERVICE_IS_MICRO_SERVICE_CALL);
        boolean isMicroServiceCall = Objects.equals("1", microServiceCallValue);
        SetMicroServiceCall.s(isMicroServiceCall);

        SetCrossDomainHeader crossDomainHeader = new SetCrossDomainHeader();
        crossDomainHeader.setRequest(request);
        crossDomainHeader.setResponse((HttpServletResponse) servletResponse);
        crossDomainHeader.sum();

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
