package com.aoeai.spin.accelerator.generate.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author aoe
 * @date 2021/3/1
 */
@Slf4j
public class YamlTools {

    /**
     * 合并Yaml文件
     *
     * @param yamlPaths yaml文件路径
     * @return 合并后的yaml文件
     * @throws FileNotFoundException
     */
    public static String mergeYaml(String... yamlPaths) throws FileNotFoundException {
        Map<String, Object> mergeMap = new HashMap<>();
        for (String yamlPath : yamlPaths) {
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.loadAs(new FileInputStream(yamlPath), Map.class);
            // 合并所有yaml文件
            for (Map.Entry<String, Object> entry : yamlMap.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                mergeMap.put(k, v);
            }
        }

        Map<String, String> resultMap = new HashMap<>(mergeMap.size());
        for (Map.Entry<String, Object> entry : mergeMap.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (v instanceof String) {
                String value = ((String) v);
                for (Map.Entry<String, Object> replace : mergeMap.entrySet()) {
                    String yamlVar = String.format("${%s}", replace.getKey());
                    value = StringUtils.replace(value, yamlVar, "" + replace.getValue());
                }
                v = value;
            } else {
                continue;
            }
            resultMap.put(k, (String) v);
        }

        // 替换所有变量
        List<String> keys = resultMap.keySet().stream().collect(Collectors.toList());
        resultMap.entrySet().stream()
                .forEach(entry -> {
                    String v = entry.getValue();
                    for (String key : keys) {
                        v = StringUtils.replace(v, String.format("${%s}", key), resultMap.get(key));
                    }
                    resultMap.put(entry.getKey(), v);
                });

        Yaml yaml = new Yaml();
        return yaml.dumpAsMap(resultMap);
    }

    /**
     * 删除Yaml中元素
     *
     * @param yamlStr       Yaml内容
     * @param removeYamlStr 被删除的Yaml内容
     * @return
     */
    public static String removeElements(String yamlStr, String removeYamlStr) {
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.loadAs(yamlStr, Map.class);
        Map<String, Object> removeMap = yaml.loadAs(removeYamlStr, Map.class);
        removeMap.forEach((k, v) -> {
            if (map.get(k) != null) {
                map.remove(k);
            }
        });
        return yaml.dumpAsMap(map);
    }

}
