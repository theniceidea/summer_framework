package com.fmk.framework.httpclient;

import com.google.common.io.Files;
import com.fmk.framework.exception.ApplicationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by jerry on 16-6-7.
 */
public class DownloadRequest extends BaseRequest {

    private byte[] result;

    public DownloadRequest(){
        super.setMethod(MethodType.GET);
    }


    public static DownloadRequest create(){
        return new DownloadRequest();
    }

    public DownloadRequest request(){
        HttpClientUtils.request(this);
        return this;
    }

    public DownloadRequest request(HttpClientService httpClientService){
        httpClientService.request(this);
        return this;
    }

    public byte[] getResult() {
        return result;
    }

    public DownloadRequest setResult(byte[] result) {
        this.result = result;
        return this;
    }

    public DownloadRequest putQueryParam(String name, String value){
        super.putQueryParam(name, value);
        return this;
    }

    public void saveAs(File file){
        try {
            Files.write(result, file);
        } catch (IOException e) {
            new ApplicationException(e);
        }
    }

    @Override
    public DownloadRequest setTimeout(int timeout) {
        super.setTimeout(timeout);
        return this;
    }

    @Override
    public DownloadRequest addHeader(String name, String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public DownloadRequest addHeaderContentTypeJSON() {
        super.addHeaderContentTypeJSON();
        return this;
    }

    @Override
    public DownloadRequest addHeaderContentTypeXML() {
        super.addHeaderContentTypeXML();
        return this;
    }

    @Override
    public DownloadRequest setUrl(String url) {
        super.setUrl(url);
        return this;
    }

    @Override
    public DownloadRequest setCharset(String charset) {
        super.setCharset(charset);
        return this;
    }

    @Override
    public DownloadRequest setContentType(String contentType) {
        super.setContentType(contentType);
        return this;
    }
}
