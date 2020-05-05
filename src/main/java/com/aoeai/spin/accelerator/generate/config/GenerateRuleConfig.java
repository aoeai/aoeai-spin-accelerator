package com.aoeai.spin.accelerator.generate.config;

import lombok.Data;

/**
 * 生成规则配置
 */
@Data
public class GenerateRuleConfig {

    // IDbConfig 数据库配置信息
    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名
     */
    private String database;

    // IBaseRule （生成时的）基础规则
    /**
     * 工程根路径的包名
     */
    private String rootPackageName;

    /**
     * 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
     */
    private String generatorRootPath;

    // IPersistentRule （生成时的）Persistent规则
    /**
     * PO(持久对象)类所在位置的包名
     */
    private String poPackageName;

    /**
     * PO(持久对象)类名后缀；不填写默认为空；生成后类名后缀与填写的一致
     */
    private String poClassNameSuffix = "";

    /**
     * 生成Java文件时需要过滤掉的表名前缀（,分割）；不填写默认为空
     */
    private String tablePrefixFilter = "";
}
