package com.testcomp.service.cms.bizdemo.demotable2;

import com.testcomp.entities0.bizdemo.summer_dev.Demotable2;
import com.testcomp.query0.bizdemo.summer_dev.SqlDemotable2;
import com.testcomp.summer.v1.cms.bizdemo.demotable2.UpdateDemotable2;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.math.BigDecimal;
import java.util.List;

@Service
@SummerService
public class UpdateDemotable2Service implements SummerServiceBean<UpdateDemotable2> {
    @Override
    public void sum(UpdateDemotable2 summer) {
//        final List<Demotable2> demotables = SqlDemotable2.inst().num_eq(summer.getNum()).queryList();
        final Demotable2 entity = SqlDemotable2.inst().num_eq(summer.getNum()).queryOne();

        entity.setTitle("title4");
        entity.setType("type4");
        entity.setNum(4);
        entity.setNum2(BigDecimal.TEN);
        entity.setDeleteStatus_deleted();
        entity.setEnableStatus_enabled();
        entity.setStatus_approved();
        entity.setStatus2_pending();

        SqlDemotable2.inst().save(entity);
//        final int count = SqlDemotable.inst().num_inc(-1).num2_inc(BigDecimal.ZERO.subtract(BigDecimal.TEN)).executeIncrement(summer.getNum());
//        System.out.println("count:"+count);
//        summer.setSummerResult(count);
    }
}
