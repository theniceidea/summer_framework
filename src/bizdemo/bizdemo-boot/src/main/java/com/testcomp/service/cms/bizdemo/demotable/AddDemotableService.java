package com.testcomp.service.cms.bizdemo.demotable;

import com.fmk.framework.basic.DateTimeUtil;
import com.fmk.framework.basic.validat.SqlValidator;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.validation.Precondition;
import com.testcomp.entities0.bizdemo.summer_dev.Demotable;
import com.testcomp.entities0.bizdemo.summer_dev2.Ds2demotable;
import com.testcomp.query0.bizdemo.summer_dev.SqlDemotable;
import com.testcomp.query0.bizdemo.summer_dev2.SqlDs2demotable;
import com.testcomp.summer.v1.cms.bizdemo.demotable.AddDemotable;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@SummerService
public class AddDemotableService implements SummerServiceBean<AddDemotable> {
    @Override
    public void sum(AddDemotable summer) {
        Demotable entity = new Demotable();
        entity.setTitle("title");
        entity.setType("type");
        entity.setNum(1);
        entity.setNum2(BigDecimal.ZERO);
        entity.setDemotable2Id(2);
        entity.setDeleteStatus_deleted();
        entity.setEnableStatus_enabled();
        entity.setStatus_approved();
        entity.setStatus2_pending();
        entity.setCreationDate(DateTimeUtil.timestampNow());

//        if(true) throw Excep.le("100010001000_错误测试");
        SqlDemotable.inst().save(entity);

        Ds2demotable entity2=new Ds2demotable();
        entity2.setTitle("title");
        entity2.setType("type");
        entity2.setNum(3);
        entity2.setNum2(BigDecimal.ZERO);
        entity2.setDeleteStatus_deleted();
        entity2.setEnableStatus_enabled();
        entity2.setStatus_approved();
        entity2.setStatus2_pending();
        entity2.setCreationDate(DateTimeUtil.timestampNow());
        SqlDs2demotable.inst().save(entity2);
    }
}
