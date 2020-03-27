package com.aoeai.spin.accelerator.refining.db.service;

import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.config.DbConfiguration;

import java.util.Map;

/**
 * 数据库所有表的信息
 */
public interface AllTables {

    /**
     * 所有表的信息
     * @return key: 表名称；value: 表信息
     */
    Map<String, Table> allTables(DbConfiguration dbConf);
}
