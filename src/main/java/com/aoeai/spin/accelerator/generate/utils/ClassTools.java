package com.aoeai.spin.accelerator.generate.utils;

/**
 * （生成）类的工具类
 */
public class ClassTools {

    /**
     * 组装包名
     * @param names 一组包名片段
     * @return 完整包名
     */
    public static final String buildPackageName(String... names){
        StringBuilder pkName = new StringBuilder();
        for (String name : names) {
            pkName.append(name).append(".");
        }
        return pkName.toString().substring(0, pkName.length() - 1);
    }


}
