package com.aoeai.spin.accelerator.generate.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;

import static com.aoeai.spin.accelerator.generate.utils.FileTools.getMainResourcesFilePath;

/**
 * 配置信息工具
 * @author aoe
 */
@Slf4j
public class ConfigTools {

    private ConfigTools() {
    }

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
        var configFile = new File(configPath);
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

        var yaml = new Yaml();
        return yaml.loadAs(yamlStr, clazz);
    }

}
