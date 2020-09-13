package com.aoeai.spin.accelerator.themes.customize.xy.finance.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 服务接口类
 * @author aoe
 * @date 2020/6/22
 */
@Data
public class XyFinServiceClass extends BaseClassProperty {

    /**
     * 分页查询对象类
     */
    private XyFinPageListQo pageListQO;

    private XyFinVO vo;

    private Po po;

    /**
     * 主键字段
     */
    private String pkColumn;
}
