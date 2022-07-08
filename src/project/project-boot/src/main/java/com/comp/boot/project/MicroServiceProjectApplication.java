package com.comp.boot.project;

import com.fmk.framework.consts.Consts;
import com.fmk.framework.foundation.bean.BeanNameGenerator;
import com.fmk.framework.web.ApplicationInit;
import com.fmk.framework.web.ControllerWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.summerframework.core.base.EnableSummer;
import org.summerframework.core.srv.ClassRewriter;

@ComponentScan(value = {"com.comp", "com.fmk"}, nameGenerator = BeanNameGenerator.class)
@SpringBootApplication
@EnableSummer
public class MicroServiceProjectApplication {

	public static void main(String[] args) {
		String moduleName="project";
		Consts.SYSTEM_COMP_NAME = "comp";
		Consts.SYSTEM_PKG_COM_COMP_PREFIX = "com."+Consts.SYSTEM_COMP_NAME;
		Consts.SYSTEM_PKG_COM_COMP_BIZ_PREFIX = "com."+Consts.SYSTEM_COMP_NAME+".biz";
		Consts.SYSTEM_PKG_COMP_CONTROLLER = "com."+Consts.SYSTEM_COMP_NAME+".controller";
		ClassRewriter.rewrite("com.fmk", "com."+Consts.SYSTEM_COMP_NAME);
		ControllerWriter.scanAndWrite("v1.cms."+moduleName,"v1.portal."+moduleName,"v0.service."+moduleName);
		ApplicationInit.boot(MicroServiceProjectApplication.class, args);
	}
}
