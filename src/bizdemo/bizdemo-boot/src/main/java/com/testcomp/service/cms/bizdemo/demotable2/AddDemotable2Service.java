package com.testcomp.service.cms.bizdemo.demotable2;

import com.testcomp.entities0.bizdemo.summer_dev.Demotable2;
import com.testcomp.query0.bizdemo.summer_dev.SqlDemotable2;
import com.testcomp.summer.v1.cms.bizdemo.demotable2.AddDemotable2;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.math.BigDecimal;

@Service
@SummerService
public class AddDemotable2Service implements SummerServiceBean<AddDemotable2> {
    @Override
    public void sum(AddDemotable2 summer) {
        Demotable2 entity = new Demotable2();
        entity.setTitle("title");
        entity.setType("type");
        entity.setNum(1);
        entity.setNum2(BigDecimal.ZERO);
        entity.setNum3(1d);
        entity.setDeleteStatus_unDeleted();
        entity.setEnableStatus_unEnabled();
        entity.setStatus_approved();
        entity.setStatus2_pending();

        SqlDemotable2.inst().save(entity);
    }
}
