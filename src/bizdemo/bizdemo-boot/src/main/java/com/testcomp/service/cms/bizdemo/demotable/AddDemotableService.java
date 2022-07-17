package com.testcomp.service.cms.bizdemo.demotable;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.DateTimeUtil;
import com.fmk.framework.restful.PageResultList;
import com.fmk.framework.validation.Valid;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.testcomp.entities0.bizdemo.summer_dev.Demotable;
import com.testcomp.enums0.bizdemo.Enum_deleteStatus;
import com.testcomp.model0.bizdemo.app.GetAppM;
import com.testcomp.model0.bizdemo.apps2.ListApps2M;
import com.testcomp.model0.bizdemo.count1.GetCount1M;
import com.testcomp.query0.bizdemo.summer_dev.SqlDemotable;
import com.testcomp.summer.v0.service.bizdemo.app.GetApp;
import com.testcomp.summer.v0.service.bizdemo.apps2.ListApps2;
import com.testcomp.summer.v0.service.bizdemo.count1.GetCount1;
import com.testcomp.summer.v1.cms.bizdemo.demotable.AddDemotable;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.math.BigDecimal;
import java.util.List;

@Service
@SummerService
public class AddDemotableService implements SummerServiceBean<AddDemotable> {
    @Override
    public void sum(AddDemotable summer) {

        final List<Demotable> demotables = SqlDemotable
                .inst()
                .num_eq(1)
//                .num_eq_fv(null).valid(Valid::notNull, "xxnum is null").back()
                .orderBy_id_asc()
                .orderBy_num2_desc()
                .queryList();

        final GetCount1M count1M = GetCount1.s(Enum_deleteStatus.unDeleted.value());
        final PageResultList<ListApps2M> sum = ListApps2
                .inst()
                .deleteStatus(1)
                .deleteStatus_valid(Valid::notNull, "")
                .deleteStatus2(Sets.newHashSet(1))
                .deleteStatus2_valid(Valid::notEmpty, "")
                .start(0)
                .limit(10)
                .sum();

        System.out.println(count1M.getCount1());
        Demotable entity = new Demotable();
        entity.title_fv("").valid(Valid::notNull).back();
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

        final GetAppM s1 = GetApp.s(Enum_deleteStatus.deleted.value(), Lists.newArrayList(1));
        final PageResultList<ListApps2M> s2 = ListApps2.s(Enum_deleteStatus.deleted.value(), Sets.newHashSet(1), 0, 10);

        final String s = JSON.toJSONString(entity);
        System.out.println(s);

//        if(true) throw Excep.le("100010001000_错误测试");
        SqlDemotable.inst().save(entity);
//
//        Ds2demotable entity2=new Ds2demotable();
//        entity2.setTitle("title");
//        entity2.setType("type");
//        entity2.setNum(3);
//        entity2.setNum2(BigDecimal.ZERO);
//        entity2.setDeleteStatus_deleted();
//        entity2.setEnableStatus_enabled();
//        entity2.setStatus_approved();
//        entity2.setStatus2_pending();
//        entity2.setCreationDate(DateTimeUtil.timestampNow());
//        SqlDs2demotable.inst().save(entity2);
    }
}
