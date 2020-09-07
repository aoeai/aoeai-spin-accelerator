package com.aoeai.spin.accelerator.themes.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器实现类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyChFacadeImpl extends BaseClassProperty {

    private XyChFacade facade;

    private XyChServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyChForm form;

    private Po po;

    private XyChPageListQo pageListQO;
}
