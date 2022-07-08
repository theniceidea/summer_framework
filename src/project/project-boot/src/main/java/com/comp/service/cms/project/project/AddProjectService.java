package com.comp.service.cms.project.project;

import com.comp.entities0.project.project_db.Project;
import com.comp.query0.project.project_db.SqlProject;
import com.comp.summer.v1.cms.project.project.AddProject;
import com.fmk.framework.validation.Precondition;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

@Service
@SummerService
public class AddProjectService implements SummerServiceBean<AddProject> {
    @Override
    public void sum(AddProject summer) {
        final Project project = SqlProject.inst().ename_eq(summer.getEname()).queryOne();
        Precondition.checkState(null == project, "此项目已存在");

        Project entity = new Project();
        entity.setCname(summer.getCname());
        entity.setEname(summer.getEname());
        entity.setDspOrder(summer.getDspOrder());
        entity.setComment(summer.getComment());
        entity.setDeleteStatus_unDeleted();
        final int ret = SqlProject.inst().save(entity);
        summer.setSummerResult(ret);
    }
}
