package com.aoeai.spin.accelerator.refining.db.service;

import com.aoeai.spin.accelerator.admin.vo.ColumnVO;
import com.aoeai.spin.accelerator.admin.vo.TableVO;
import com.aoeai.spin.accelerator.refining.db.bean.Table;

import java.util.List;

/**
 * 数据库服务
 * @author aoe
 * @date 2020/6/7
 */
public interface DBService {

    /**
     * 获得所有表
     * @param tableName
     * @return
     */
    List<TableVO> getTableList(String tableName);

    /**
     * 获得表的Column信息
     * @param tableName
     * @return 所有列的信息
     */
    List<ColumnVO> getColumnList(String tableName);

    /**
     * 获得Table对象
     * @param tableName
     * @return
     */
    Table getTable(String tableName);
}
