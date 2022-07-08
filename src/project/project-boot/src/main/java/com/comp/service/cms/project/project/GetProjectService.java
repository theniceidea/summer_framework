package com.comp.service.cms.project.project;

import com.comp.entities0.project.project_db.Project;
import com.comp.query0.project.project_db.SqlProject;
import com.comp.summer.v1.cms.project.project.GetProject;
import com.fmk.framework.validation.Precondition;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

@Service
@SummerService
public class GetProjectService implements SummerServiceBean<GetProject> {
    @Override
    public void sum(GetProject summer) {
        final Project entity = SqlProject.inst().id_eq(summer.getId()).queryOne();
        Precondition.checkState(null != entity, "此项目不存在");
        summer.setSummerResult(entity);
    }
}
