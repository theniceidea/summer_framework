//package com.fmk.framework.permission.config;
//
//import JwtService;
//import UserAuthInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @author larry
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private PermissionFilter permissionFilter;
//
////    @Autowired
////    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors()
//
//                .and()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
////                .and().exceptionHandling()
////                    .authenticationEntryPoint(userAuthenticationEntryPoint)
//
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/**").permitAll();
//
////        http.addFilterBefore(permissionFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
////    @Bean
////    public UserAuthInterceptor userAuthInterceptor() {
////        return new UserAuthInterceptor();
////    }
////
////    @Bean
////    public JwtService jwtService() {
////        return new JwtService();
////    }
//}
