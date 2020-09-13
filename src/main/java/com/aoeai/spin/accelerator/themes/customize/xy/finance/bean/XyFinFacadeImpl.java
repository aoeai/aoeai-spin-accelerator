package com.aoeai.spin.accelerator.themes.customize.xy.finance.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器实现类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyFinFacadeImpl extends BaseClassProperty {

    private XyFinFacade facade;

    private XyFinServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyFinForm form;

    private Po po;

    private XyFinPageListQo pageListQO;
}
