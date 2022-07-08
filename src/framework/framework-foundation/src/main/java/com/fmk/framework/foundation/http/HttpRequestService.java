package com.fmk.framework.foundation.http;

import com.fmk.framework.http.PostContent;
import com.fmk.framework.http.Request;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class HttpRequestService {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private OkHttpClient okHttpClient;

    public HttpRequestService(){
        okHttpClient = new OkHttpClient().newBuilder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                cookieStore.put(httpUrl.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                return cookies != null ? cookies : new ArrayList<>();
            }
        }).build();
    }
    public void request(Request request){

        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(request.getUrl());
        buildHeader(request, builder);

        buildCookie(request);

        PostContent postContent = request.getPostContent();
        if("get".equals(StringUtils.lowerCase(request.getMethod()))){
            builder.get();
        }else if("post".equals(StringUtils.lowerCase(request.getMethod()))){
            if(Objects.equals(PostContent.FORM, postContent)){
                Object data = request.getRequestData();
                if(data instanceof HashMap){

                }
            }
        }

    }

    private void buildCookie(Request request) {
        HttpUrl httpUrl = HttpUrl.parse(request.getUrl());
        ArrayList<Cookie> cookies = new ArrayList<>();
        request.getCookies().forEach((key, cookie)->{
            Cookie.Builder cookieBuilder = new Cookie.Builder();
            cookieBuilder
                .name(cookie.getName())
                .value(cookie.getValue())
                .domain(cookie.getDomain())
                .expiresAt(cookie.getExpiresAt())
                .path(cookie.getPath());
            if(cookie.isSecure()){
                cookieBuilder.secure();
            }
            if(cookie.isHttpOnly()){
                cookieBuilder.httpOnly();
            }
            cookies.add(cookieBuilder.build());
        });
        cookieStore.put(httpUrl.host(), cookies);
    }

    private void buildHeader(Request request, okhttp3.Request.Builder builder) {
        request.getHeaders().forEach((key, values) -> {
            values.forEach(value->{
                builder.addHeader(key, value);
            });
        });
    }
}
