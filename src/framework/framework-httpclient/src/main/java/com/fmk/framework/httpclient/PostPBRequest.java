package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.exception.Excep;
import org.apache.http.NoHttpResponseException;

import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class PostPBRequest extends BaseRequest {

    private String result;

    private byte[] postData;

    public static PostPBRequest create(){
        return new PostPBRequest();
    }
    public PostPBRequest(){
        super.setMethod(MethodType.POST);
    }

    public PostPBRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public PostPBRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }
    public PostPBRequest request(Class<?> errorClass, String errorMessage){
        try {
            HttpClientUtils.request(this);
        }catch (ApplicationException ae){
            if(ae.is(NoHttpResponseException.class)) throw Excep.le(errorClass, errorMessage);
            throw ae;
        }
        return this;
    }

    public Map<String, Object> parseMap(){
        return JSON.parseObject(result);
    }
    public <T> T parse(Class<T> tClass){
        return JSONParser.parseObject(result, tClass);
    }

    public PostPBRequest setPostData(byte[] postData){
        this.postData = postData;
        return this;
    }

    public byte[] getPostData() {
        return postData;
    }

    public String getResult() {
        return result;
    }

    public PostPBRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public PostPBRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public PostPBRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public PostPBRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public PostPBRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public PostPBRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public PostPBRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public PostPBRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public PostPBRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
