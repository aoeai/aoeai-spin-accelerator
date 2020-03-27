package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.dao.bean.Po;
import com.aoeai.spin.accelerator.refining.db.bean.Table;

import java.util.Map;

/**
 * 创建持久对象
 */
public interface BuildPO {

    /**
     * 创建PO对象
     * @param allTables 所有表的信息 key: 表名；value: 表信息
     * @return 所有PO信息 key: 表名；value: PO对象的数据
     */
    Map<String, Po> allPOMap(Map<String, Table> allTables);
}
