package com.aoeai.spin.accelerator.generate.common;

import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;

/**
 * （生成时的）基础规则
 */
public interface IBaseRule {

    /**
     * 生成规则配置
     * @return
     */
    GenerateRuleConfig grConfig();

    /**
     * @return 工程根路径的包名
     */
    String rootPackageName();

    /**
     * @return 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
     */
    String generatorRootPath();

    /**
     * @return 主题文件夹名称（例如：resources/themes/base，返回 base)
     */
    String themes();

    /**
     * 表名
     * @return
     */
    String tableName();

    /**
     * @return 生成Java文件时需要过滤掉的表名前缀（,分割）
     */
    String tablePrefixFilter();

    /**
     * yaml 文件名称
     * @return
     */
    String yamlName();

    /**
     *
     * @return 模块名称
     */
    String modelName();
}
