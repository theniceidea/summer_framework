package com.comp.summer.v1.cms.project.project;

import com.fmk.framework.annotations.Publish;
import com.fmk.framework.annotations.SkipUserAuth;
import com.fmk.framework.annotations.validation.NotBlank;
import com.fmk.framework.annotations.validation.NotNull;
import com.fmk.framework.summer.BasicSummer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Publish
@SkipUserAuth
public class AddProject extends BasicSummer<Integer> {
    /**
     * 中文名
     */
    @NotNull(msg="请输入中文名称")
    @NotBlank(msg = "请输入中文名称")
    private String cname;
    /**
     * 英文名
     */
    @NotNull(msg="请输入英文名称")
    @NotBlank(msg = "请输入英文名称")
    private String ename;
    /**
     * 序号
     */
    private Integer dspOrder;
    /**
     * 备注
     */
    private String comment;

}
