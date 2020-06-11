package com.aoeai.spin.accelerator.refining.db.po;

import lombok.Data;

/**
 * 列
 * @author aoe
 * @date 2020/6/7
 */
@Data
public class ColumnPO {

    private String columnName;

    private String dataType;

    private String columnComment;

    private String columnKey;

    private String extra;
}
