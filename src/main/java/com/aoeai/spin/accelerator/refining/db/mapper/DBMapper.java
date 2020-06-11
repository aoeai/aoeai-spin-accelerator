package com.aoeai.spin.accelerator.refining.db.mapper;

import com.aoeai.spin.accelerator.refining.db.po.ColumnPO;
import com.aoeai.spin.accelerator.refining.db.po.TablePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库接口
 * @author aoe
 * @date 2020/6/5
 */
public interface DBMapper {

    /**
     * 查询Table信息
     * @param tableName
     * @return
     */
    TablePO selectTable(@Param("tableName") String tableName);

    /**
     * 查询Table列表信息
     * @param tableName
     * @return
     */
    List<TablePO> selectTableList(@Param("tableName") String tableName);

    /**
     * 查询Column信息
     * @param tableName 表名
     * @return
     */
    List<ColumnPO> selectColumnList(String tableName);
}
