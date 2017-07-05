package com.heitian.ssm.arch.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * 使用spring Resoures文件加载机制加载文件
 * @author walden
 */
public class ClassPathFileLoader {
    /**
     * 获得所有匹配文件
     * @param path
     * @return
     */
    public static Resource[] getResources(String path) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resolver.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    /**
     * 获得一个匹配文件
     * @param path
     * @return
     */
    public static Resource getResource(String path) {
        Resource[] resources=getResources(path);
        return resources.length>0?resources[0]:null;
    }
}
