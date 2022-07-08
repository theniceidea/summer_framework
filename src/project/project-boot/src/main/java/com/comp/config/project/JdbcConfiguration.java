package com.comp.config.project;

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
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void sum(DaoJdbcTemplate model){
        if(Objects.equals(model.getName(), "project_db")){
            model.setSummerResult(jdbcTemplate);
        }
    }
}
