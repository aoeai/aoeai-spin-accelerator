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

    private String columnComment;

    private String columnKey;

    private String extra;
}
