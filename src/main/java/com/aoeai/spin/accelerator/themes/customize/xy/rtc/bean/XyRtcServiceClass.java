package com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 服务接口类
 * @author aoe
 * @date 2020/6/22
 */
@Data
public class XyRtcServiceClass extends BaseClassProperty {

    /**
     * 分页查询对象类
     */
    private XyRtcPageListQo pageListQO;

    private XyRtcVO vo;

    private Po po;

    /**
     * 主键字段
     */
    private String pkColumn;
}
