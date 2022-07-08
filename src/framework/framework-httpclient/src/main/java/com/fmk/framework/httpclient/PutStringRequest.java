package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;

import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class PutStringRequest extends BaseRequest {

    private String result;

    private String postData;

    public static PutStringRequest create(){
        return new PutStringRequest();
    }
    public PutStringRequest(){
        super.setMethod(MethodType.PUT);
    }

    public PutStringRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public PutStringRequest request(HttpClientService httpClientService){
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

    public PutStringRequest setPostData(String postData){
        this.postData = postData;
        return this;
    }

    public String getResult() {
        return result;
    }

    public PutStringRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public PutStringRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public PutStringRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public PutStringRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public PutStringRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public PutStringRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public PutStringRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public PutStringRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public PutStringRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
