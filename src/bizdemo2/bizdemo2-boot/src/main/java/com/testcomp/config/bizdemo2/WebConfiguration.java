package com.testcomp.config.bizdemo2;
import com.fmk.framework.web.JwtService;
import com.fmk.framework.web.UserAuthInterceptor;
import com.fmk.framework.web.JwtService;
import com.fmk.framework.web.UserAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public UserAuthInterceptor userAuthInterceptor(){
        return new UserAuthInterceptor();
    }
    @Bean
    public JwtService jwtService(){
        return new JwtService();
    }
}
