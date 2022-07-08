package com.fmk.framework.basic;

public class Summer2HttpInfo {
    private String url;
    private String method;
    private String moduleName;
    private String varName;

    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    public String getModuleName() {
        return moduleName;
    }

    protected void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getVarName() {
        return varName;
    }

    protected void setVarName(String varName) {
        this.varName = varName;
    }
}
