package com.testcomp.msql0.bizdemo.app;
import com.testcomp.summer.v0.service.bizdemo.app.GetApp;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import com.fmk.framework.validation.Precondition;
import com.fmk.framework.basic.validat.SqlValidator;
import java.util.Collection;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.daosimple.BQuerySelect;
import org.summerframework.model.SummerServiceBean;
import org.apache.commons.lang3.StringUtils;
import com.testcomp.model0.bizdemo.app.*;
import java.util.List;


/**
* 备注1
*/
@Service
@SummerService
public class GetAppService implements SummerServiceBean<GetApp> {
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private Object ds(){
        if(null != instDbTarget__){
            return instDbTarget__;
        }

        if(null != DB_TARGET){
            return DB_TARGET;
        }
        DB_TARGET=DaoJdbcTemplate.s("summer_dev");
        return DB_TARGET;
    }

    @Override
    public void sum(GetApp summer) {
        final BQuerySelect bqs = new BQuerySelect();
        final StringBuilder builder = bqs.getBuilder();
        final List<Object> values = bqs.getParameters();
        boolean bol=true;
        bqs.setSelect("select a.id 'abc', a.status, a.num, a.num2, a.delete_status");
        builder.append(" from demotable a");
        builder.append(" where true");

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        Precondition.checkState(bol, "100010001000_xxxxxxx");
        builder.append(" and a.delete_status=?");
        values.add(summer.getDeleteStatus());

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        if(bol){
            builder.append(" and a.delete_status=?");
            values.add(summer.getDeleteStatus());
        }
        builder.append(" and a.delete_status is null");

        bol=true;
        bol=bol && SqlValidator.notEmpty(summer.getDs());
        Precondition.checkState(bol, "100010001000_xxxxxxx");
        String txt = StringUtils.repeat("?", ",", summer.getDs().size());
        builder.append(" and a.delete_status in (:list_ds)".replace(":list_ds", txt));
        values.addAll(summer.getDs());
        

        final List<GetAppM> list = DaoList.s(ds(), GetAppM.class, bqs, 0, 1);
        if(list.size()>0){
            summer.setSummerResult(list.get(0));
        }

    }
}

