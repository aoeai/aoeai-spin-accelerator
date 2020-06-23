package com.aoeai.spin.accelerator.generate.web.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 */
@Data
public class Form extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<FormField> fieldList;

    // 自定义规则时用
    private PO po;

    /**
     * （数据库的）表
     */
    private Table table;
}
