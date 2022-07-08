package com.fmk.framework.deploy;

import java.util.HashMap;
import java.util.Map;

public class DeployConfig {
    private String normalVersion;
    private String grayVersion;
    private String version1;
    private String version2;
    private Map<String, DeployService> services = new HashMap<>();

    public String getNormalVersion() {
        return normalVersion;
    }

    public void setNormalVersion(String normalVersion) {
        this.normalVersion = normalVersion;
    }

    public String getGrayVersion() {
        return grayVersion;
    }

    public void setGrayVersion(String grayVersion) {
        this.grayVersion = grayVersion;
    }

    public Map<String, DeployService> getServices() {
        return services;
    }

    public void setServices(Map<String, DeployService> services) {
        this.services = services;
    }

    public String getVersion1() {
        return version1;
    }

    public void setVersion1(String version1) {
        this.version1 = version1;
    }

    public String getVersion2() {
        return version2;
    }

    public void setVersion2(String version2) {
        this.version2 = version2;
    }

}
