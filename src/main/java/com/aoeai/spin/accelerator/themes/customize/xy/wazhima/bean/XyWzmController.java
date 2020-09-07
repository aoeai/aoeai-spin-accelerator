package com.aoeai.spin.accelerator.themes.customize.xy.wazhima.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 控制器类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyWzmController extends BaseClassProperty {

    /**
     * url 前缀
     */
    private String urlPrefix;

    private XyWzmServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyWzmForm form;

    private Po po;

    private XyWzmPageListQo pageListQO;

}
