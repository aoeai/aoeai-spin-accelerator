package com.aoeai.spin.accelerator.generate.utils;

import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import org.yaml.snakeyaml.Yaml;

/**
 * 配置信息工具
 */
public class ConfigTools {

    /**
     * 从yaml文件中获取配置信息
     * @param yamlName （resources目录下）yaml文件名
     * @return GenerateRuleConfig对象
     */
    public static GenerateRuleConfig getGenerateRuleConfig(String yamlName) {
        Yaml yaml = new Yaml();
        GenerateRuleConfig grConfig =
                yaml.loadAs(ConfigTools.class.getResourceAsStream(yamlName),GenerateRuleConfig.class);
        return grConfig;
    }
}
