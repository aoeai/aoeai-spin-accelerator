package com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 门面控制器实现类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyRtcManageFacadeImpl extends BaseClassProperty {

    private XyRtcManageFacade facade;

    private XyRtcManageServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private XyRtcForm form;

    private Po po;

    private XyRtcPageListQo pageListQO;

    /**
     * url 请求后缀
     */
    private String actionSuffix;
}
