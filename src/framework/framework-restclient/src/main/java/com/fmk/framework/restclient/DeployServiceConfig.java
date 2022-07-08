package com.fmk.framework.restclient;

import com.fmk.framework.deploy.DeployConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author larry
 * @date 2019/3/1
 */
@Component
@ConfigurationProperties(prefix = "app")
public class DeployServiceConfig {
    private DeployConfig deploy;

    public DeployConfig getDeploy() {
        return deploy;
    }

    public void setDeploy(DeployConfig deploy) {
        this.deploy = deploy;
    }
}
