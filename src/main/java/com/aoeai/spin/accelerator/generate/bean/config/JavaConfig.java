package com.aoeai.spin.accelerator.generate.bean.config;

import lombok.Data;

/**
 * Java 文件配置信息
 * @author aoe
 * @date 2020/8/24
 */
@Data
public class JavaConfig {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类存储位置
     */
    private String filePath;

    /**
     * 类名前缀；不填写默认为空；生成后类名后缀与填写的一致
     */
    private String prefix = "";

    /**
     * 类名后缀；不填写默认为空；生成后类名后缀与填写的一致
     */
    private String suffix = "";

    /**
     * 模板文件路径
     */
    private String templates;

}
