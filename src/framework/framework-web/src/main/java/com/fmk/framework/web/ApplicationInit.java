package com.fmk.framework.web;

import com.fmk.framework.logger.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInit implements ApplicationContextAware {
    private static final Logger logger = Logger.getLogger(ApplicationInit.class);

    @Autowired
    private DocumentService documentService;
    @Autowired
    private GetRightsService getRightsService;

    public static void boot(Class kls, String[] args){
        try {
            SpringApplication.run(kls, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            ControllerWriter.register(applicationContext);

            documentService.buildDocJson(applicationContext);
            getRightsService.buildRightsJson(applicationContext);
        }catch (Throwable e){
            logger.error(e.getMessage(), e);
        }
    }
}
