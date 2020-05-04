package com.aoeai.spin.accelerator.generate.dao.bean;

import lombok.Data;

/**
 * （数据库对应的）实体类字段
 */
@Data
public class POField {

    /**
     * 字段类型：类名简称（无包名）
     */
    private String classShortName;

    /**
     * 字段类型：类名全称（有包名）
     */
    private String classFullName;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 注释
     */
    private String comment;
}
