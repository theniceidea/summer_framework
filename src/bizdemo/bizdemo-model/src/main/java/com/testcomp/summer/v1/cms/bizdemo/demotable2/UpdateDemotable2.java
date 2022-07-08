package com.testcomp.summer.v1.cms.bizdemo.demotable2;

import com.fmk.framework.annotations.ApiInfo;
import com.fmk.framework.annotations.Publish;
import com.fmk.framework.summer.BasicSummer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Publish
@ApiInfo("修改Demotable")
public class UpdateDemotable2 extends BasicSummer<Object> {
    @ApiInfo("numfilter")
    private Integer num=0;

//    @NotNull(msg = "000000000023_nums size err2")
//    @Size(min=1, max = 3, msg = "000000000023_nums size err")
//    private List<Integer> nums;

}
