package com.aoeai.spin.accelerator.themes1.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 * @author aoe
 */
@Data
public class XyChForm extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<XyChFormField> fieldList;
}
