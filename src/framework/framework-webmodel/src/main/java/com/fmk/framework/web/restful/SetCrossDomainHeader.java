package com.fmk.framework.web.restful;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class SetCrossDomainHeader extends BasicSummer<Void> implements LocalSummer{
    private Object request;
    private Object response;

    public static void s(Object request, Object response){
        SetCrossDomainHeader setCrossDomainHeader = new SetCrossDomainHeader();
        setCrossDomainHeader.setRequest(request);
        setCrossDomainHeader.setResponse(response);
        setCrossDomainHeader.sum();
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
