package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;

import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class GetRequest extends BaseRequest {

    private String result;


    public GetRequest(){
        super.setMethod(MethodType.GET);
    }

    public static GetRequest create(){
        return new GetRequest();
    }

    public GetRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public GetRequest request(HttpClientService httpClientService){
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

    public GetRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public GetRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    @Override
    public GetRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public GetRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public GetRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public GetRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public GetRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public GetRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public GetRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
