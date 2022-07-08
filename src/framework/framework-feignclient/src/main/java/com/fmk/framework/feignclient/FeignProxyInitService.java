package com.fmk.framework.feignclient;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.summerframework.core.srv.ServiceInstall;

//@Component
public class FeignProxyInitService implements ApplicationContextAware {

    @Autowired
    private ServiceInstall serviceInstall;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] names = applicationContext.getBeanNamesForAnnotation(FeignClient.class);

        for(int i = 0; i < names.length; ++i) {
            String beanName = names[i];
            Object bean = applicationContext.getBean(beanName);
            Class<?> targetClass = AopUtils.getTargetClass(bean);
            if (!targetClass.getName().contains("com.sun.proxy.$Proxy")) continue;

            serviceInstall.installBean(bean);
        }
    }
}
