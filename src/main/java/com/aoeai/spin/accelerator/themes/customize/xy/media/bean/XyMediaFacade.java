package com.aoeai.spin.accelerator.themes.customize.xy.media.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器接口
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyMediaFacade extends BaseClassProperty {

    private XyMediaServiceClass serviceClass;

    private XyMediaForm form;

    private Po po;

    private XyMediaPageListQo pageListQO;
}
