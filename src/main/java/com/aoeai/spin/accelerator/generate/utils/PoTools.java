package com.aoeai.spin.accelerator.generate.utils;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.google.common.base.Preconditions;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * PO 对象工具
 * @author aoe
 * @date 2021/7/23
 */
public class PoTools {

    /**
     * 设置第一次出现的主键信息
     * @param po
     * @return
     */
    public final static Po setFirstPrimaryKey(Po po) {
        Preconditions.checkNotNull(po);
        Table table = po.getTable();
        Preconditions.checkNotNull(table);
        List<Column> columns = table.getColumns();
        if (CollectionUtils.isEmpty(columns)) {
            return po;
        }

        Optional<Column> firstPrimaryKey = columns.stream()
                .filter(column -> column.getIsPrimaryKey())
                .findFirst();
        if (firstPrimaryKey.isEmpty()) {
            return po;
        }

        Column column = firstPrimaryKey.get();
        po.setFirstPrimaryKey(column.getHumpName());
        po.setFirstPrimaryKeyColumn(column.getName());

        return po;
    }
}
