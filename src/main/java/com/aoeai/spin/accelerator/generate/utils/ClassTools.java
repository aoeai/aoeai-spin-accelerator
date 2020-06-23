package com.aoeai.spin.accelerator.generate.utils;

import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * （生成）类的工具类
 */
public class ClassTools {

    /**
     * 组装包名
     * @param names 一组包名片段
     * @return 完整包名
     */
    public static String buildPackageName(String... names){
        StringBuilder pkName = new StringBuilder();
        for (String name : names) {
            pkName.append(name).append(".");
        }
        return pkName.toString().substring(0, pkName.length() - 1);
    }

    /**
     * 根据表名获取类名
     *
     * @param tableName 表名
     * @param filterPrefix 生成Java文件时需要过滤掉的表名前缀（,分割）
     * @param classNameSuffix 类名后缀，不填写默认为空；生成后类名后缀与填写的一致
     * @return
     */
    public static String buildClassName(String tableName, String filterPrefix, String classNameSuffix) {
        if (filterPrefix != null) {
            for (String str : filterPrefix.split(",")) {
                tableName = tableName.toLowerCase().replace(str.toLowerCase(), "");
            }
        }

        return StringUtils.capitalize(humpName(tableName)) + classNameSuffix;
    }

    /**
     * 驼峰命名
     * @param name _ 分割的名称
     * @return 驼峰试命名
     */
    public static String humpName(String name) {
        if (!name.contains("_")) {
            return name;
        }

        name = name.toLowerCase();
        while (name.contains("_")) {
            int i = name.indexOf("_");
            name = name.substring(0, i)
                    + name.substring(++i, ++i).toUpperCase()
                    + name.subSequence(i, name.length());
        }

        return name;
    }

    /**
     * 组装import语句列表
     * @param columns
     * @return
     */
    public static Set<String> buildImportList(List<Column> columns){
        Set<String> result = new LinkedHashSet<>();
        for (Column column : columns) {
            String dbType = column.getType();
            String fullName = MySQLType2JavaTypeEnum.javaType(dbType, column.getLength()).fullName();
            if (fullName == null) {
                continue;
            }
            result.add(fullName);
        }
        return result;
    }

    /**
     * 根据表名获得模块名称
     * @param tableName
     * @param filterPrefix
     * @return
     */
    public static String getModelName(String tableName, String filterPrefix) {
        String className = buildClassName(tableName, filterPrefix, "");
        return className.toLowerCase();
    }
}
