package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.exception.Excep;
import org.apache.http.NoHttpResponseException;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class PostFormRequest extends BaseRequest {

    private String result;

    private List<BasicNameValuePair> postData;

    public static PostFormRequest create(){
        return new PostFormRequest();
    }
    public PostFormRequest(){
        super.setMethod(MethodType.POST);
    }

    public PostFormRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public PostFormRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }
    public PostFormRequest request(Class<?> errorClass, String errorMessage){
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

    public PostFormRequest addPostData(String name, String value){
        if(null == postData) postData = new ArrayList<>(5);
        if(null == value) return this;
        postData.add(new BasicNameValuePair(name, value));
        return this;
    }

    public List<BasicNameValuePair> getPostData() {
        return postData;
    }

    public String getResult() {
        return result;
    }

    public PostFormRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public PostFormRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public PostFormRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public PostFormRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public PostFormRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public PostFormRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public PostFormRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public PostFormRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public PostFormRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
