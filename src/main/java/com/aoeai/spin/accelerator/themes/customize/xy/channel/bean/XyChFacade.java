package com.aoeai.spin.accelerator.themes.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器接口
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyChFacade extends BaseClassProperty {

    private XyChServiceClass serviceClass;

    private XyChForm form;

    private Po po;

    private XyChPageListQo pageListQO;
}
