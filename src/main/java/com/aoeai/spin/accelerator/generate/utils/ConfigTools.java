package com.aoeai.spin.accelerator.generate.utils;

import org.yaml.snakeyaml.Yaml;

/**
 * 配置信息工具
 * @author aoe
 */
public class ConfigTools {

    /**
     * 从yaml文件中获取配置信息
     * @param yamlName （resources目录下）yaml文件名
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getConfig(String yamlName, Class<T> clazz) {
        Yaml yaml = new Yaml();
        return yaml.loadAs(ConfigTools.class.getResourceAsStream(yamlName), clazz);
    }
}
