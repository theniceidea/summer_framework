package com.fmk.framework.foundation.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.asm.ClassReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class ClassHelper {

    public static void scanPackageClass(String basePackage, Consumer<ClassMeta> consumer) {
        try {
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            String DEFAULT_RESOURCE_PATTERN = "**/*.class";
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/" + DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try(BufferedInputStream is = new BufferedInputStream(resource.getInputStream())){
                        ClassReader classReader = new ClassReader(is);
                        ClassMeta classMeta = new ClassMeta();
                        classMeta.setClassName(convertResourcePathToClassName(classReader.getClassName()));
                        classMeta.setSuperName(convertResourcePathToClassName(classReader.getSuperName()));
                        consumer.accept(classMeta);
                    }
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private static String convertResourcePathToClassName(String resourcePath) {
        if(StringUtils.isEmpty(resourcePath)) { return ""; };
        return resourcePath.replace('/', '.');
    }
}
