package com.testcomp.msql0.bizdemo.count1;
import java.util.List;
import com.testcomp.summer.v0.service.bizdemo.count1.GetCount1;
import com.testcomp.model0.bizdemo.count1.*;
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
* 备注1
*/
@Service
@SummerService
public class GetCount1Service implements SummerServiceBean<GetCount1> {
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
    public void sum(GetCount1 summer) {
        final BQuerySelect bqs = new BQuerySelect();
        final StringBuilder builder = bqs.getBuilder();
        final List<Object> values = bqs.getParameters();
        boolean bol=true;
        bqs.setSelect("select count(1) 'count1'");
        builder.append(" from demotable a");
        builder.append(" where true");

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        Precondition.checkState(bol, "100010001000_xxxxxxx");
        builder.append(" and a.delete_status=?");
        values.add(summer.getDeleteStatus());
        if(0 == summer.getDeleteStatus() && summer.getDeleteStatus()>0 && null != summer.getParam2()){

        bol=true;
        bol=bol && SqlValidator.notNull(summer.getDeleteStatus());
        Precondition.checkState(bol, "100010001000_xxxxxxx");
        builder.append(" and a.delete_status=?");
        values.add(summer.getDeleteStatus());
        }//}
        if(0 == summer.getDeleteStatus() && summer.getDeleteStatus()>0 && null != summer.getParam2()){

        bol=true;
        if(bol){
            builder.append(" and a.delete_status=?; #");
            values.add(summer.getDeleteStatus());
        }
        }

        final List<GetCount1M> list = DaoList.s(ds(), GetCount1M.class, bqs, 0, 1);
        if(list.size()>0){
            summer.setSummerResult(list.get(0));
        }

    }
}

