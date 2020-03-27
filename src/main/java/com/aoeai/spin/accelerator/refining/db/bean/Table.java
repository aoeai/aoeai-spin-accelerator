package com.aoeai.spin.accelerator.refining.db.bean;

import lombok.Data;

import java.util.List;

/**
 * （数据库的）表
 */
@Data
public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 注释
     */
    private String comment;

    /**
     * 列
     */
    private List<Column> columns;
}
