package com.aoeai.spin.accelerator.generate.persistent.rule;

/**
 * （生成时的）Persistent规则
 */
public interface IPersistentRule {

    /**
     * @return PO(持久对象)类所在位置的包名后缀
     */
    String poPackageSuffix();

    /**
     * @return PO(持久对象)类名后缀，不填写默认为空；生成后类名后缀与填写的一致
     */
    String poClassNameSuffix();

    /**
     * @return 生成Java文件时需要过滤掉的表名前缀（,分割）
     */
    String tablePrefixFilter();

}
