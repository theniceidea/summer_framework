package com.fmk.framework.restful;

import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.SetResponseHeader;
import com.fmk.framework.web.IsMicroServiceCall;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class ResponseBodyWrap implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrap(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(
        Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
        NativeWebRequest nativeWebRequest
    ) throws Exception {
        if(!ClassUtil.isAppClass(methodParameter.getDeclaringClass())){
            delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
            return;
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String skipresultwrap = (String)requestAttributes.getAttribute(Consts.REQUEST_SKIP_RESULT_WRAP,
                                                          RequestAttributes.SCOPE_REQUEST);
        if(StringUtils.isNotBlank(skipresultwrap)){
            delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
            return;
        }
        boolean bol = IsMicroServiceCall.s();
        Class<?> parameterType = methodParameter.getParameterType();
        if(bol){
            SetResponseHeader.s(Consts.SERVICE_RESULT_CODE, "0");
        }
        if(byte[].class.equals(parameterType)) {
            delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        }else if(bol){
            HttpServletRequest request = requestAttributes.getRequest();
            String header = request.getHeader(Consts.SERVICE_RESTTEMPLATE_CLIENT);
            if("1".equals(header)){
                RestTemplateClientResultWrapModel result = new RestTemplateClientResultWrapModel();
                result.setSummerResult(o);
                delegate.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
            }else{
                delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
            }
        }else {
            RestResult<Object> result = new RestResult<>();
            result.setCode("0");
            result.setResult(o);
            delegate.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
