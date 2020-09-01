package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 */
@Data
public class XyWzmForm extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<XyWzmFormField> fieldList;

}
