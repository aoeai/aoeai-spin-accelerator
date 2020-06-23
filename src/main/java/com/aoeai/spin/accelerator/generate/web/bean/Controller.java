package com.aoeai.spin.accelerator.generate.web.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import lombok.Data;

/**
 * 控制器类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class Controller extends BaseClassProperty {

    /**
     * url 前缀
     */
    private String urlPrefix;

    private ServiceClass serviceClass;

    /**
     * ServiceClass 变量名称
     */
    private String serviceClassVariable;

    private Form form;

    private PO po;

    private PageListQO pageListQO;

}
