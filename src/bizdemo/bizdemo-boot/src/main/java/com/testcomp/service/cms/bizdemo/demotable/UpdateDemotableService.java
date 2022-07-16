package com.testcomp.service.cms.bizdemo.demotable;

import com.testcomp.entities0.bizdemo.summer_dev.Demotable;
import com.testcomp.query0.bizdemo.summer_dev.SqlDemotable;
import com.testcomp.summer.v1.cms.bizdemo.demotable.UpdateDemotable;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.util.List;

@Service
@SummerService
public class UpdateDemotableService implements SummerServiceBean<UpdateDemotable> {
    @Override
    public void sum(UpdateDemotable summer) {
        final List<Demotable> demotables = SqlDemotable
                .inst()
                .num_eq(summer.getNum())
                .orderBy_id_asc()
                .orderBy_num2_desc()
                .queryList();
        System.out.println("");



//        final String userid = CurrentUserId.s(false);
//        System.out.println(userid);
//        final Demotable entity = SqlDemotable.inst().id_eq(2).queryOne();

//        entity.setTitle("title4");
//        entity.setType("type4");
//        entity.setNum(4);
//        entity.setNum2(BigDecimal.TEN);
//        entity.setDemotable2Id(4);
//        entity.setDeleteStatus_deleted();
//        entity.setEnableStatus_enabled();
//        entity.setStatus_approved();
//        entity.setStatus2_pending();
//        entity.setCreationDate(DateTimeUtil.timestampNow());

//        SqlDemotable.inst().save(entity);
//        final int count = SqlDemotable.inst()
//                .num_inc(-1)
//                .num2_inc(BigDecimal.ZERO.subtract(BigDecimal.TEN))
//                .id_eq(summer.getNum())
//                .executeIncrementUnChangeThrow("LError_文本错误");
//        System.out.println("count:"+count);
//        summer.setSummerResult(count);
    }
}
