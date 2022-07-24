package com.testcomp.msql0.bizdemo.apps2;
import com.testcomp.summer.v0.service.bizdemo.apps2.ListApps2;
import com.testcomp.model0.bizdemo.apps2.*;
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
import com.fmk.framework.restful.PageResultList;


/**
* 备注3
*/
@Service
@SummerService
public class ListApps2Service implements SummerServiceBean<ListApps2> {
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
    public void sum(ListApps2 summer) {
        final BQuerySelect bqs = new BQuerySelect();
        final StringBuilder builder = bqs.getBuilder();
        final List<Object> values = bqs.getParameters();
        boolean bol=true;
        bqs.setSelect("select a.id, a.status, a.num, a.num2");
        builder.append(" from demotable a");
        builder.append(" where true");

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        Precondition.checkState(bol, "100010001000_xxxxxxx0");
        builder.append(" and a.delete_status=?");
        values.add(summer.getDeleteStatus());

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus2());
        bol=bol && SqlValidator.notEmpty(summer.getDeleteStatus2());
        Precondition.checkState(bol, "100010001000_xxxxxxx1");
        String txt = StringUtils.repeat("?", ",", summer.getDeleteStatus2().size());
        builder.append(" and a.delete_status in (:set_deleteStatus2)".replace(":set_deleteStatus2", txt));
        values.addAll(summer.getDeleteStatus2());
        

        final PageResultList<ListApps2M> list = DaoPageList.s(ds(), ListApps2M.class, bqs, summer.getStart(), summer.getLimit());
        summer.setSummerResult(list);

    }
}

