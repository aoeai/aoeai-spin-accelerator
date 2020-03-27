package com.aoeai.spin.accelerator.generate.dao;

/**
 * （生成时的）Dao规则
 */
public interface IDaoRule {

    /**
     * @return PO(持久对象)类的包名后缀
     */
    String poPackageSuffix();

    /**
     * @return 生成Java文件时需要过滤掉的表名前缀（,分割）
     */
    String tablePrefixFilter();

}
