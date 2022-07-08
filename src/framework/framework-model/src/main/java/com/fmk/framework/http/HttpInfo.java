package com.fmk.framework.http;

import com.fmk.framework.summer.BasicSummer;

import java.util.List;
import java.util.TreeMap;

public class HttpInfo<T> extends BasicSummer<T> {
    private TreeMap<String, List<String>> headers = new TreeMap<>();
    private TreeMap<String, HttpCookie> cookies = new TreeMap<>();

    public TreeMap<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(TreeMap<String, List<String>> headers) {
        this.headers = headers;
    }

    public TreeMap<String, HttpCookie> getCookies() {
        return cookies;
    }

    public void setCookies(TreeMap<String, HttpCookie> cookies) {
        this.cookies = cookies;
    }
}
