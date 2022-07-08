package com.comp.service.cms.project.project;

import com.comp.entities0.project.project_db.Project;
import com.comp.query0.project.project_db.SqlProject;
import com.comp.summer.v1.cms.project.project.DeleteProject;
import com.fmk.framework.validation.Precondition;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

@Service
@SummerService
public class DeleteProjectService implements SummerServiceBean<DeleteProject> {
    @Override
    public void sum(DeleteProject summer) {
        final Project entity = SqlProject.inst().id_eq(summer.getId()).queryOne();
        Precondition.checkState(null != entity, "此项目不存在");
        Precondition.checkState(entity.deleteStatus_is_unDeleted(), "此项目已被删除");

        entity.setDeleteStatus_deleted();
        final int ret = SqlProject.inst().save(entity);
        summer.setSummerResult(ret);
    }
}
