package com.fmk.framework.restful;

import com.fasterxml.jackson.databind.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class AppConfiguration {

    @Bean
    public ResponseBodyWrapFactory getResponseBodyWrap() {
        return new ResponseBodyWrapFactory();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder)
    {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

        return objectMapper;
    }
    @Bean
    public FilterRegistrationBean appFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.setFilter(new AppFilter());
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean exceptionHandlerFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new ExceptionHandlerFilter());
        return filterRegistrationBean;
    }
}
