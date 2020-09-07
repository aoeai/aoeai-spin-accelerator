package com.aoeai.spin.accelerator.generate.bean.config;

import lombok.Data;

/**
 * PO（数据库对应的）持久对象 配置信息
 * @author aoe
 * @date 2020/8/24
 */
@Data
public class PoConfig extends JavaConfig {

    /**
     * 生成Java文件时需要过滤掉的表名前缀（,分割）；不填写默认为空
     */
    private String tablePrefixFilter = "";
}
