package com.testcomp.config.bizdemo;

import com.fmk.framework.daomodel.DaoJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;

import java.util.Objects;

@Service
@SummerService
public class JdbcConfiguration {
    public static final String DB_TARGET_SUMMER_DEV="summer_dev";
    public static final String DB_TARGET_SUMMER_DEV2="summer_dev2";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcTemplateTwo")
    private JdbcTemplate jdbcTemplateTwo;

    public void sum(DaoJdbcTemplate model){
        if(Objects.equals(model.getName(), "summer_dev2")){
            model.setSummerResult(jdbcTemplateTwo);
        }else if(Objects.equals(model.getName(), "summer_dev")){
            model.setSummerResult(jdbcTemplate);
        }
    }
}
