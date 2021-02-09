package com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 * @author aoe
 */
@Data
public class XyDsForm extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<XyDsFormField> fieldList;
}
