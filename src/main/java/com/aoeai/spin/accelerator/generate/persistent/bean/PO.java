package com.aoeai.spin.accelerator.generate.persistent.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import lombok.Data;

import java.util.List;

/**
 * （数据库对应的）持久对象
 */
@Data
public class PO extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<POField> fieldList;

    // 自定义规则时用
    /**
     * （数据库的）表
     */
    private Table table;
}
