package com.fmk.framework.restclient;

import cn.hutool.core.util.URLUtil;
import com.fmk.framework.daomodel.CurrentTransactionId;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.session.CurrentUserId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = Logger.getLogger(HeaderInterceptor.class);

    @Override
    public ClientHttpResponse intercept(
        HttpRequest request, byte[] body, ClientHttpRequestExecution execution
    ) throws IOException {
        HttpHeaders headers = request.getHeaders();

        String userId = CurrentUserId.s(true);
        String transactionId = CurrentTransactionId.s(true);
        headers.add(HttpHeaders.ACCEPT, "application/json;charset=UTF-8");
        headers.add(Consts.SERVICE_IS_MICRO_SERVICE_CALL, "1");
        if (null != userId) {
            headers.add(Consts.SERVICE_USER_ID, userId);
        }

        if (null != transactionId) {
            headers.add(Consts.SERVICE_TRANSACTION_ID, transactionId);
        }

        try {
            logger.info("rest request:"+transactionId);
            ClientHttpResponse response = execution.execute(request, body);
            HttpHeaders httpHeaders = response.getHeaders();
            String code = httpHeaders.getFirst(Consts.SERVICE_RESULT_CODE);

            if(!"0".equals(code)){
                String msg = URLUtil.decode(Objects.requireNonNull(httpHeaders.getFirst(Consts.SERVICE_RESULT_MSG)));
                throw Excep.le(this.getClass(), code, msg);
            }

            return response;
        }catch (RuntimeException e){
            logger.info("rest request err:"+transactionId);
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
