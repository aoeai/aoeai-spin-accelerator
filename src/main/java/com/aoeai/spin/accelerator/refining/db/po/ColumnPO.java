package com.aoeai.spin.accelerator.refining.db.po;

import lombok.Data;

/**
 * 列 https://dev.mysql.com/doc/refman/8.0/en/columns-table.html
 * @author aoe
 * @date 2020/6/7
 */
@Data
public class ColumnPO {

    private String columnName;

    private String dataType;

    /**
     * For string columns, the maximum length in characters.
     */
    private Integer characterMaximumLength;

    /**
     * The DATA_TYPE value is the type name only with no other information.
     * The COLUMN_TYPE value contains the type name and possibly other information such as the precision or length.
     */
    private String columnType;

    private String columnComment;

    private String columnKey;

    private String extra;

    private String isNullable;
}
