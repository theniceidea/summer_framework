package com.fmk.framework.httpclient;


import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;

import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class DeleteRequest extends BaseRequest {

    private String result;


    public DeleteRequest(){
        super.setMethod(MethodType.DELETE);
    }

    public static DeleteRequest create(){
        return new DeleteRequest();
    }

    public DeleteRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public DeleteRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }

    public Map<String, Object> parseMap(){
        return JSON.parseObject(result);
    }
    public <T> T parse(Class<T> tClass){
        return JSONParser.parseObject(result, tClass);
    }

    public String getResult() {
        return result;
    }

    public DeleteRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public DeleteRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public DeleteRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public DeleteRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public DeleteRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public DeleteRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public DeleteRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public DeleteRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public DeleteRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
