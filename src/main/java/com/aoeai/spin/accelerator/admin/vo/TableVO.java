package com.aoeai.spin.accelerator.admin.vo;

import lombok.Data;

/**
 * 表
 * @author aoe
 * @date 2020/6/5
 */
@Data
public class TableVO {

    private String tableName;

    private String tableComment;

    private String engine;

    private String createTime;
}
