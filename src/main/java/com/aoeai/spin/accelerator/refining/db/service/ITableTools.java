package com.aoeai.spin.accelerator.refining.db.service;

import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfiguration;

import java.util.Map;

/**
 * （数据库）表的工具类
 */
public interface ITableTools {

    /**
     * 所有表的信息
     * @return key: 表名称；value: 表信息
     */
    Map<String, Table> allTables(IDbConfiguration dbConf);
}
