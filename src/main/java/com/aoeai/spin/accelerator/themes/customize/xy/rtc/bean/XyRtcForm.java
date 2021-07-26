package com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import lombok.Data;

import java.util.List;

/**
 * 表单对象
 * @author aoe
 */
@Data
public class XyRtcForm extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<XyRtcFormField> fieldList;
}
