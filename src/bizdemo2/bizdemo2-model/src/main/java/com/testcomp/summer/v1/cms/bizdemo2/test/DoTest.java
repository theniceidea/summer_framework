package com.testcomp.summer.v1.cms.bizdemo2.test;

import com.fmk.framework.annotations.ApiInfo;
import com.fmk.framework.annotations.Publish;
import com.fmk.framework.annotations.SkipUserAuth;
import com.fmk.framework.summer.BasicSummer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Publish
@SkipUserAuth
@ApiInfo("dotest")
public class DoTest extends BasicSummer<Void> {

}
