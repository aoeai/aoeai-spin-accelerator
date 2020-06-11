package com.aoeai.spin.accelerator.refining.db.po;

import lombok.Data;

import java.util.Date;

/**
 * @author aoe
 * @date 2020/6/5
 */
@Data
public class TablePO {

    private String tableName;

    private String tableComment;

    private String engine;

    private Date createTime;
}
