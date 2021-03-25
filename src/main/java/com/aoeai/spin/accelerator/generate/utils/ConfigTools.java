package com.aoeai.spin.accelerator.generate.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.regex.Matcher;

import static com.aoeai.spin.accelerator.generate.utils.FileTools.getMainResourcesFilePath;

/**
 * 配置信息工具
 * @author aoe
 */
@Slf4j
public class ConfigTools {

    /**
     * 从yaml文件中获取配置信息
     * @param yamlName （resources目录下）yaml文件名
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getConfig(String yamlName, Class<T> clazz) {
        String yamlPath = getMainResourcesFilePath(yamlName);
        String configPath = yamlName.substring(0, yamlName.lastIndexOf("/")) + "/base-config.yaml";
        configPath = StringUtils.replace(configPath,"/", File.separator);
        configPath = getMainResourcesFilePath(configPath);
        File configFile = new File(configPath);
        boolean existsConfigFile = configFile.exists();
        String yamlStr = null;
        try {
            if (existsConfigFile) {
                yamlStr = YamlTools.mergeYaml(configPath, yamlPath);
                yamlStr = YamlTools.removeElements(yamlStr, YamlTools.mergeYaml(configPath));
            } else {
                yamlStr = YamlTools.mergeYaml(yamlPath);
            }
        } catch (FileNotFoundException e) {
            log.error("合并Yaml文件失败", e);
        }
        Yaml yaml = new Yaml();
        return yaml.loadAs(yamlStr, clazz);
    }

    /**
     * 从yaml文件中获取配置信息
     * @param baseConfigYaml 自定义变量配置文件
     * @param yamlName （resources目录下）yaml文件名
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T>T getConfig(String baseConfigYaml, String yamlName, Class<T> clazz) {
        Yaml yaml = new Yaml();
        Map<String, Object> baseCfgMap = yaml.loadAs(ConfigTools.class.getResourceAsStream(baseConfigYaml), Map.class);

        String yamlStr = null;
        try {
            yamlStr = FileTools.readFileToString(new File(getMainResourcesFilePath(yamlName)));
        } catch (IOException e) {
            log.error("获取配置文件失败", e);
        }

        // 替换自定义变量
        for (Map.Entry<String, Object> entry : baseCfgMap.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (v instanceof String) {
                yamlStr = yamlStr.replaceAll(Matcher.quoteReplacement(k), "" + v);
            }
        }

        return yaml.loadAs(new StringReader(yamlStr), clazz);
    }

}
