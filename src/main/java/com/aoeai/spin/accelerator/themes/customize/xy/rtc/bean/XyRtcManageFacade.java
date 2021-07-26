package com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * api-manage 门面控制器接口
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyRtcManageFacade extends BaseClassProperty {

    private XyRtcManageServiceClass serviceClass;

    private XyRtcForm form;

    private Po po;

    private XyRtcPageListQo pageListQO;
}
