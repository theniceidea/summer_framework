package com.fmk.framework.feignclient;

import cn.hutool.core.util.URLUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.daomodel.CurrentTransactionId;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.session.CurrentUserId;
import com.fmk.framework.web.CurrentRequestId;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * 参考https://www.jianshu.com/p/fc4d4348dc58
 */
public class OkHttp3Interceptor implements Interceptor {
    private static final Logger logger = Logger.getLogger(OkHttp3Interceptor.class);

    public OkHttp3Interceptor(){
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        String userId = (String) requestAttributes.getAttribute(Consts.REQUEST_CURRENT_USER_ID_KEY, RequestAttributes.SCOPE_REQUEST);
        String userId = CurrentUserId.s(true);
//        String requestId = (String) requestAttributes.getAttribute(Consts.SERVICE_REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
        String requestId = CurrentRequestId.s(true);
//        String transactionId = (String) requestAttributes.getAttribute(Consts.SERVICE_TRANSACTION_ID, RequestAttributes.SCOPE_REQUEST);
        String transactionId = CurrentTransactionId.s(true);

        Request.Builder builder = request.newBuilder();
        builder.header("Accept", "application/json;charset=UTF-8");
        builder.header(Consts.SERVICE_IS_MICRO_SERVICE_CALL, "1");
        if(null != userId) { builder.header(Consts.SERVICE_USER_ID, userId); }
        if(null != requestId) { builder.header(Consts.SERVICE_REQUEST_ID, requestId); }
        if(null != transactionId) { builder.header(Consts.SERVICE_TRANSACTION_ID, transactionId); }
        Request build = builder.build();

//        long t1 = System.nanoTime();
//        System.out.println(String.format("Sending request %s on %s%n%s",
//            build.url(), chain.connection(), build.headers()));
//        Response response = null;
//        try {
//            logger.info("feign request:"+transactionId);
//            response = chain.proceed(build);
//        }catch (Exception e){
//            logger.info("feign request err:"+transactionId);
//            logger.error(e.getMessage(), e);
//            throw e;
//        }
        Response response = chain.proceed(build);
        String code = response.header(Consts.SERVICE_RESULT_CODE);

        if(!"0".equals(code)){
            String msg = URLUtil.decode(response.header(Consts.SERVICE_RESULT_MSG));
            requestAttributes.setAttribute(Consts.SERVICE_RESULT_CODE, code, RequestAttributes.SCOPE_REQUEST);
            requestAttributes.setAttribute(Consts.SERVICE_RESULT_MSG, msg, RequestAttributes.SCOPE_REQUEST);
        }

//        long t2 = System.nanoTime();
//        System.out.println(String.format("Received response for %s in %.1fms%n%s",
//            response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
