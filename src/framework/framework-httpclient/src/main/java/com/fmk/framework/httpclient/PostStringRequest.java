package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;

import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class PostStringRequest extends BaseRequest {

    private String result;

    private String postData;

    public static PostStringRequest create(){
        return new PostStringRequest();
    }
    public PostStringRequest(){
        super.setMethod(MethodType.POST);
    }

    public PostStringRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public PostStringRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }

    public Map<String, Object> parseMap(){
        return JSON.parseObject(result);
    }
    public <T> T parse(Class<T> tClass){
        return JSONParser.parseObject(result, tClass);
    }

    public String getPostData(){
        return this.postData;
    }

    public PostStringRequest setPostData(String postData){
        this.postData = postData;
        return this;
    }

    public String getResult() {
        return result;
    }

    public PostStringRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public PostStringRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public PostStringRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public PostStringRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public PostStringRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public PostStringRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public PostStringRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public PostStringRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public PostStringRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
