package com.fmk.framework.http;

import java.util.ArrayList;
import java.util.Objects;

public class RequestInfo<T> extends HttpInfo<T>{
    private String method;
    private PostContent postContent = PostContent.FORM;
    private String url;
    private Object requestData;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getRequestData() {
        return requestData;
    }

    public void setRequestData(Object requestData) {
        this.requestData = requestData;
    }

    public PostContent getPostContent() {
        return postContent;
    }

    public void setPostContent(PostContent postContent) {
        ArrayList<String> list = new ArrayList<>(1);
        if(Objects.equals(postContent, PostContent.JSON)){
            list.add("application/json");
        }else{
            list.add("application/x-www-form-urlencoded");
        }
        this.getHeaders().put("Content-Type",  list);
        this.postContent = postContent;
    }
}
