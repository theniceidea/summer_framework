package com.comp.config.project;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    DataSource ds() {
//        return new DruidDataSource();
//    }
//
//
//    @Bean
//    JdbcTemplate jdbcTemplate(@Qualifier("ds") DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }

}
