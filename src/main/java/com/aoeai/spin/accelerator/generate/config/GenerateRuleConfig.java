package com.aoeai.spin.accelerator.generate.config;

import lombok.Data;

/**
 * 生成规则配置
 * @author aoe
 */
@Data
public class GenerateRuleConfig {

    // --------------------------------------- IBaseRule （生成时的）基础规则 ---------------------------------------
    /**
     * 工程根路径的包名
     */
    private String rootPackageName;

    /**
     * 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
     */
    private String generatorRootPath;

    /**
     * 主题文件夹名称（例如：resources/themes/base，返回 base)
     */
    private String themes;

    // --------------------------------------- IPersistentRule （生成时的）Persistent规则 ---------------------------------------
    /**
     * PO(持久对象)类存储位置
     */
    private String poPath;

    /**
     * PO(持久对象)类名后缀；不填写默认为空；生成后类名后缀与填写的一致
     */
    private String poClassNameSuffix = "";

    /**
     * 生成Java文件时需要过滤掉的表名前缀（,分割）；不填写默认为空
     */
    private String tablePrefixFilter = "";

    /**
     * Mybatis Mapper 文件路径
     */
    private String mapperPath;

    /**
     * Mybatis Mapper类名后缀；不填写默认为空；生成后类名后缀与填写的一致
     */
    private String mapperClassNameSuffix;

    /**
     * Mybatis XML 文件路径
     */
    private String mapperXmlPath;

    /**
     * Mybatis Plus Service 类存储位置
     */
    private String mapperServicePath;

    /**
     * Mybatis Plus Service 类后缀
     */
    private String mapperServiceSuffix;

    /**
     * Mybatis Plus ServiceImpl 类存储位置
     */
    private String mapperServiceImplPath;

    /**
     * Mybatis Plus ServiceImpl 类后缀
     */
    private String mapperServiceImplSuffix;

    // --------------------------------------- Service  ---------------------------------------
    /**
     * Service 类存储位置
     */
    private String servicePath;

    /**
     * Service 类后缀
     */
    private String serviceSuffix;

    //  --------------------------------------- Web ---------------------------------------

    /**
     * 页面查询类存储位置
     */
    private String pageListQOPath;

    /**
     * 页面查询类名后缀
     */
    private String pageListQOClassNameSuffix;

    /**
     * VO 类存储位置
     */
    private String voPath;

    /**
     * VO 类名后缀
     */
    private String voClassNameSuffix;
}
