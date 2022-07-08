package com.fmk.framework.httpclient;

import com.fmk.framework.basic.AbstractEnum;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 16-6-7.
 */
public class BaseRequest {

    private static final int TIME_OUT = 5000;

    private int timeout=TIME_OUT;
    private String url;
    private String methodType;
    private List<Header> headers;
    private String charset = Charsets.UTF_8.name();
    private String contentType = null;
    private List<Header> responseHeaders;

    private List<BasicNameValuePair> queryParams;

    private RequestResultStatus status;

    public int getTimeout() {
        return timeout;
    }

    public BaseRequest setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public BaseRequest putQueryParam(String name, String value){
        if(null == queryParams) queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair(name, value));
        return this;
    }

    public List<BasicNameValuePair> getQueryParams(){
        return this.queryParams;
    }

    public String buildUrl(){
        String s = getUrl();
        List<BasicNameValuePair> queryParams = getQueryParams();
        if(!CollectionUtils.isEmpty(queryParams)){
            String format = URLEncodedUtils.format(queryParams, Charsets.UTF_8);
            if(!StringUtils.isBlank(format)) {
                s = s + (s.contains("?") ? "&" : "?") + format;
            }
        }
        return s;
    }


    public boolean isStatusOK(){
        return status.getStatusCode()==HttpStatus.SC_OK;
    }

    public String getMethod() {
        return methodType;
    }
    protected BaseRequest setMethod(MethodType method){
        this.methodType=method.getTitle();
        return this;
    }

    public BaseRequest addHeader(String name, String value){
        if(null == headers) headers = new ArrayList<>(3);
        headers.add(new BasicHeader(name, value));
        return this;
    }
    public BaseRequest addHeaderContentTypeJSON(){
        addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.contentType=MediaType.APPLICATION_JSON_VALUE;
        return this;
    }
    public BaseRequest addHeaderContentTypeXML(){
        addHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE);
        this.contentType=MediaType.TEXT_XML_VALUE;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public BaseRequest setContentType(String contentType) {
        addHeader(HttpHeaders.CONTENT_TYPE, contentType);
        this.contentType = contentType;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BaseRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public BaseRequest setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public RequestResultStatus getStatus() {
        return status;
    }

    public List<Header> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(List<Header> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public void setStatus(RequestResultStatus status) {
        this.status = status;
    }

    public static final class MethodType extends AbstractEnum<Integer> {
        public static final MethodType GET = new MethodType(0, "GET");
        public static final MethodType POST = new MethodType(1, "POST");
        public static final MethodType PUT = new MethodType(2, "PUT");
        public static final MethodType DELETE = new MethodType(3, "DELETE");

        MethodType(Integer value, String title) {
            super(value, title);
        }
    }
}
