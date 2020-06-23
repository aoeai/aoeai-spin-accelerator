package com.aoeai.spin.accelerator.admin.vo;

import lombok.Data;

/**
 * 列
 * @author aoe
 * @date 2020/6/7
 */
@Data
public class ColumnVO {

    private String columnName;

    private String dataType;

    /**
     * For string columns, the maximum length in characters.
     */
    private Integer characterMaximumLength;

    private String columnType;

    private String columnComment;

    private String columnKey;

    private String extra;

    /**
     * 整数类型的最大长度
     */
    private Integer integersLength;

    /**
     * 数据库设置的最大长度
     */
    private Integer dbMaxLength;
}
