package com.aoeai.spin.accelerator.refining.db.service;

import com.aoeai.spin.accelerator.refining.db.bean.Table;

import java.util.Map;

/**
 *  数据库的表的服务
 */
@Deprecated
public interface DBTableService {

    /**
     * 所有表的信息
     * @return key: 表名称；value: 表信息
     */
    Map<String, Table> allTables();
}
