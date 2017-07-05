package com.heitian.ssm.arch.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类用来加载缓存配置文件信息，默认配置文件调整后，重新缓存
 */
@Slf4j
public class PropsLoadCacheUtils {

    private static Map<String, Long> filePathlastModifiedMap = new HashMap<>();
    private static Map<String, Map<String, String>> filePathPropertiesMap = new HashMap<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    /**
     * 将配置文件配置文件加载后缓存在系统中
     *
     * @param filePath           相对于classpath的路径 classpath:${filePath}.properties
     * @param ignoreLastModified 是否忽略文件最后调整时间 如果是true，第一次加载之后每次都是从缓存中取出；
     *                           否则，每次加载的时候都要判断文件被缓存后是否调整过，如果调整过就重新缓存配置文件
     */
    public static Map<String, String> load(String filePath, boolean ignoreLastModified) {
        Assert.isTrue(StringUtils.isNotBlank(filePath), "配置文件路径filePath不能为空!");
        String classPath = String.format("classpath:%s.properties", filePath);
        Resource resource = new DefaultResourceLoader().getResource(classPath);
        if (!resource.exists()) {
            log.error(classPath + " 文件不存在!");
        } else {
            long lastModified = 0;
            try {
                lastModified = resource.lastModified();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long originLastModified = filePathlastModifiedMap.get(filePath) == null ? 0l : filePathlastModifiedMap.get(filePath);
            //如果文件没有被加载缓存过 或者 调整了文件,如果忽略了时间戳,不考虑文件调整值缓存一次
            if (filePathPropertiesMap.get(filePath) == null || (!ignoreLastModified && (lastModified != originLastModified))) {
                log.info(String.format("加载配置文件:[%s]，文件的最后调整时间为:[%s]", classPath, sdf.format(new Date(lastModified))));
                final Map<String, String> propertiesMap = new PropertiesLoader(resource).getPropertiesMap();
                filePathPropertiesMap.put(filePath, propertiesMap);
                filePathlastModifiedMap.put(filePath, lastModified);
            }
        }
        return filePathPropertiesMap.get(filePath);
    }

    /**
     * 将配置文件配置文件加载后缓存在系统中,如果文件修改过，重新加载
     *
     * @param filePath 相对于classpath的路径 classpath:${filePath}.properties
     * @return
     */
    public static Map<String, String> load(String filePath) {
        return load(filePath, false);
    }

}
