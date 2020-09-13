package com.aoeai.spin.accelerator.themes.customize.xy.finance.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器接口
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyFinFacade extends BaseClassProperty {

    private XyFinServiceClass serviceClass;

    private XyFinForm form;

    private Po po;

    private XyFinPageListQo pageListQO;
}
