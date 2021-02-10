package com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 控制器类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyDsController extends BaseClassProperty {

    private XyDsServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyDsForm formCreate;

    private XyDsForm formUpdate;

    private Po po;

    private XyDsPageListQo pageListQO;

    /**
     * 路径中的名称
     */
    private String pathName;
}
