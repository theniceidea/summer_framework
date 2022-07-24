package com.testcomp.msql0.bizdemo.apps;
import com.testcomp.summer.v0.service.bizdemo.apps.ListApps;
import com.testcomp.model0.bizdemo.apps.*;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import com.fmk.framework.validation.Precondition;
import com.fmk.framework.basic.validat.SqlValidator;
import java.util.Collection;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.daosimple.BQuerySelect;
import org.summerframework.model.SummerServiceBean;
import org.apache.commons.lang3.StringUtils;
import java.util.List;


/**
* 备注2
*/
@Service
@SummerService
public class ListAppsService implements SummerServiceBean<ListApps> {
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
    public void sum(ListApps summer) {
        final BQuerySelect bqs = new BQuerySelect();
        final StringBuilder builder = bqs.getBuilder();
        final List<Object> values = bqs.getParameters();
        boolean bol=true;
        bqs.setSelect("select a.id, a.status, a.num, a.num2");
        builder.append(" from demotable a");
        builder.append(" where true");

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        Precondition.checkState(bol, "100010001000_xxxxxxx");
        builder.append(" and a.delete_status=?");
        values.add(summer.getDeleteStatus());

        final List<ListAppsM> list = DaoList.s(ds(), ListAppsM.class, bqs);
        summer.setSummerResult(list);

    }
}

