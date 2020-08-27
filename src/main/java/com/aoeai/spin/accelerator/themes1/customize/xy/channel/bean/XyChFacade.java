package com.aoeai.spin.accelerator.themes1.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import lombok.Data;

/**
 * 门面控制器接口
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyChFacade extends BaseClassProperty {

    private XyChServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyChForm form;

    private Po po;

    private PageListQO pageListQO;
}
