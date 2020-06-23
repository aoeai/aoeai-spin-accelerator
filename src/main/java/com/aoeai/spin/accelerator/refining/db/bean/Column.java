package com.aoeai.spin.accelerator.refining.db.bean;

import lombok.Data;

/**
 * (数据库的)列
 */
@Data
public class Column {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段最大长度
     */
    private Integer length;

    /**
     * 是否主键 true:是
     */
    private boolean isPrimaryKey;

    /**
     * 允许为空 true:是
     */
    private boolean isNullable;

    /**
     * 注释
     */
    private String comment;

    /**
     * For string columns, the maximum length in characters.
     */
    private Integer characterMaximumLength;

    /**
     * 整数类型的最大长度
     */
    private Integer integersLength;
}
