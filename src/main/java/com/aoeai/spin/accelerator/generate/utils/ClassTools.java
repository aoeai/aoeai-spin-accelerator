package com.aoeai.spin.accelerator.generate.utils;

import org.apache.commons.lang3.StringUtils;

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
     * 根据表名获取Po的类名
     *
     * @param tableName 表名
     * @param filterPrefix 生成Java文件时需要过滤掉的表名前缀（,分割）
     * @param poClassNameSuffix PO(持久对象)类名后缀，不填写默认为空；生成后类名后缀与填写的一致
     * @return
     */
    public static String getPoClassName(String tableName, String filterPrefix, String poClassNameSuffix) {
        if (filterPrefix != null) {
            for (String str : filterPrefix.split(",")) {
                tableName = tableName.toLowerCase().replace(str.toLowerCase(), "");
            }
        }

        return StringUtils.capitalize(humpName(tableName)) + poClassNameSuffix;
    }

    /**
     * 驼峰命名
     * @param name _ 分割的名称
     * @return 驼峰试命名
     */
    public static String humpName(String name) {
        name = name.toLowerCase();
        while (name.contains("_")) {
            int i = name.indexOf("_");
            name = name.substring(0, i)
                    + name.substring(++i, ++i).toUpperCase()
                    + name.subSequence(i, name.length());
        }

        return name;
    }

}
