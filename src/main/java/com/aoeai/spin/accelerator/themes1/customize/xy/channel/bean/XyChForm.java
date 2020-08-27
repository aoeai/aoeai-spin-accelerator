package com.aoeai.spin.accelerator.themes1.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.web.bean.FormField;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 */
@Data
public class XyChForm extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<FormField> fieldList;
}
