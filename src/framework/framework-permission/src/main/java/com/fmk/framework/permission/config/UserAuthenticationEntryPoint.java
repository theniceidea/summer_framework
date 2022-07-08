//package com.fmk.framework.permission.config;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import RestResult;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static ErrMsgs.Err_000200000016;
//import static javax.servlet.http.HttpServletResponse.SC_OK;
//
///**
// * @author larry
// * @date 2019/2/20
// */
//@Component
//public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
//            throws IOException, ServletException {
//        httpServletResponse.setStatus(SC_OK);
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        RestResult<Void> result = new RestResult<>();
//        result.setCode(Err_000200000016.getCode());
//        result.setMsg(Err_000200000016.getMsg());
//
//        httpServletResponse.getOutputStream().write(JSON.toJSONBytes(result, SerializerFeature.UseISO8601DateFormat));
//    }
//}
