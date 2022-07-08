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
public class DeleteProject extends BasicSummer<Integer> {
    /**
     * id
     */
    @NotNull(msg = "请输入id")
    @NotBlank(msg = "请输入id")
    private String id;

}
