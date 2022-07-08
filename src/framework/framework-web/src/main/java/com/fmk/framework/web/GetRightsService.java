package com.fmk.framework.web;

import com.alibaba.fastjson.JSONArray;
import com.fmk.framework.annotations.ApiInfo;
import com.fmk.framework.annotations.PreAuthorize;
import com.fmk.framework.annotations.SkipUserAuth;
import com.fmk.framework.basic.ClassUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.logger.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;

@RestController
public class GetRightsService {
    private static final Logger logger = Logger.getLogger(GetRightsService.class);

    private static final JSONArray root = new JSONArray();

    @GetMapping("/v1/cms/rights")
    @ApiInfo("获取权限接口")
    @SkipUserAuth
    public JSONArray getRights() {
        return root;
    }
    @GetMapping("/feign/v1/cms/rights")
    @ApiInfo("获取权限接口")
    public JSONArray getRightsFeign() {
        return root;
    }

    protected void buildRightsJson(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForAnnotation(RestController.class);
        for (String name : names) {
            Object bean = applicationContext.getBean(name);

            Class<?> targetClass = AopUtils.getTargetClass(bean);
            if (targetClass.getName().contains("$$")) {
                targetClass = targetClass.getSuperclass();
            }
            if (!ClassUtil.isAppClass(targetClass)) {
                continue;
            }

            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                HashMap<String, Object> methodJson = new HashMap<>();

                if (null != method.getAnnotation(Deprecated.class)) {
                    continue;
                }

                PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
                if (null == preAuthorize) {
                    continue;
                }

                methodJson.put("authority", preAuthorize.value());
                methodJson.put("name", preAuthorize.name());
                root.add(methodJson);
            }
        }
    }

    public static void main(String[] args) {
        String str = "com."+Consts.SYSTEM_COMP_NAME+".controller.cms.user.adminuser.AdminUserResetPasswordController";
        System.out.println(str.substring(Consts.SYSTEM_PKG_COMP_CONTROLLER.length() + 1, str.lastIndexOf(Consts.SYSTEM_DOT_SEPARATOR) + 1));
    }

}
