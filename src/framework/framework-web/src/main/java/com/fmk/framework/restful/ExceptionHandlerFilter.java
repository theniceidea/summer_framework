package com.fmk.framework.restful;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.ErrorMsgCodes;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.exception.LogicException;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.web.SetResponseHeader;
//import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.fmk.framework.web.IsMicroServiceCall;
//import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import javax.servlet.*;
import java.io.IOException;

import static java.util.Objects.nonNull;

//@Component
public class ExceptionHandlerFilter implements Filter{
    private static final Logger logger = Logger.getLogger(ExceptionHandlerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
        ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
    ) throws IOException {
        boolean isMicroService = IsMicroServiceCall.s();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            if(e instanceof HttpMediaTypeNotAcceptableException){
                logger.warn(e.getMessage(), e);
                return;
            }
            RestResult<Void> result = new RestResult<>();
//            AuthenticationException authenticationException = Excep.findSuperException(e, AuthenticationException.class);
//            if(nonNull(authenticationException)) {
//                result.setCode(Err_000000000010.getCode());
//                result.setMsg(Err_000000000010.getMsg());
//            }

            LogicException le = null;
//            HystrixRuntimeException hre = Excep.findSuperException(e, HystrixRuntimeException.class);
//            if(nonNull(hre)) {
//                le = Excep.findSuperException(hre.getFallbackException(), LogicException.class);
//            }

            if (nonNull(le)) {
                result.setCode(le.getExceptionCode());
                result.setMsg(le.getMessage());
            } else{
//                result.setCode(Excep.code(ErrMsgs.Err_000000000009));
//                result.setMsg(Excep.msg(ErrMsgs.Err_000000000009));
                result.setCode(ErrorMsgCodes.getErrCode(ErrMsgs.Err_000000000009));
                result.setMsg(ErrMsgs.Err_000000000009);
                result.setMsg(LogicException.replaceLanguageMsg(result.getCode(), result.getMsg()));
            }
            if(isMicroService){
                SetResponseHeader.s(Consts.SERVICE_RESULT_CODE, result.getCode());
                SetResponseHeader.s(Consts.SERVICE_RESULT_MSG, URLUtil.encode(result.getMsg()));
            }
//            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().write(JSON.toJSONString(result));
        }
    }

    @Override
    public void destroy() {

    }
}
