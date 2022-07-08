package com.fmk.framework.httpclient;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.JSONParser;

import java.io.File;
import java.util.Map;

/**
 * Created by jerry on 16-6-7.
 */
public class PostFileRequest extends BaseRequest {

    private String result;
    private File postFile;
    private String inputName;

    public PostFileRequest(){
        super.setMethod(MethodType.POST);
    }

    public static PostFileRequest create(){
        return new PostFileRequest();
    }
    public PostFileRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public PostFileRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }

    public PostFileRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    public Map<String, Object> parseMap(){
        return JSON.parseObject(result);
    }
    public <T> T parse(Class<T> tClass){
        return JSONParser.parseObject(result, tClass);
    }

    public PostFileRequest setPostFile(String filePath){
        this.postFile = new File(filePath);
        return this;
    }

    public PostFileRequest setPostFile(File postFile){
        this.postFile = postFile;
        return this;
    }

    public File getPostFile() {
        return postFile;
    }


    public String getResult() {
        return result;
    }

    public PostFileRequest setResult(String result) {
        this.result = result;
        return this;
    }

    public String getInputName() {
        return inputName;
    }

    public PostFileRequest setInputName(String inputName) {
        this.inputName = inputName;
        return this;
    }

    @Override
    public PostFileRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public PostFileRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public PostFileRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public PostFileRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public PostFileRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public PostFileRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public PostFileRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
