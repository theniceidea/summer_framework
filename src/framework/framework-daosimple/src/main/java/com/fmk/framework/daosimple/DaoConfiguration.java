//package com.fmk.framework.daosimple;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DaoConfiguration {
//
//    @Value("${spring.datasource.url}")
//    private String dataSourceUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dataSourceUserName;
//
//    @Value("${spring.datasource.password}")
//    private String dataSourcePassword;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String dataSourceDriverClassName;
//
//    @Value("${spring.datasource.initial-size}")
//    private int dataSourceInitialSize;
//
//    @Value("${spring.datasource.max-active}")
//    private int dataSourceMaxActive;
//
//    @Value("${spring.datasource.min-idle}")
//    private int dataSourceMinIdle;
//
//    @Value("${spring.datasource.max-wait}")
//    private int dataSourceMaxWait;
//
//    @Bean(initMethod="init",destroyMethod="close")
//    public DruidDataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(dataSourceUrl);
//        dataSource.setUsername(dataSourceUserName);
//        dataSource.setPassword(dataSourcePassword);
//        dataSource.setDriverClassName(dataSourceDriverClassName);
//        dataSource.setInitialSize(dataSourceInitialSize);
//        dataSource.setMaxActive(dataSourceMaxActive);
//        dataSource.setMinIdle(dataSourceMinIdle);
//        dataSource.setMaxWait(dataSourceMaxWait);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setPoolPreparedStatements(false);
//        return dataSource;
//    }
//}
