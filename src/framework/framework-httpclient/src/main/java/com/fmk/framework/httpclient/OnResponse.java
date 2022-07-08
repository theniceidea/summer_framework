package com.fmk.framework.httpclient;

import org.apache.http.HttpResponse;

/**
 * Created by jerry on 16-6-8.
 */
@FunctionalInterface
public interface OnResponse {
    void exec(HttpResponse response);
}
