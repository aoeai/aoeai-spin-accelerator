package com.aoeai.spin.accelerator.themes.customize.xy.media.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器实现类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyMediaFacadeImpl extends BaseClassProperty {

    private XyMediaFacade facade;

    private XyMediaServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyMediaForm form;

    private Po po;

    private XyMediaPageListQo pageListQO;
}
