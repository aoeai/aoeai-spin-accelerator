package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 服务类
 * @author aoe
 * @date 2020/6/22
 * 移动到自定义模块
 */
@Data
public class XyWzmServiceClass extends BaseClassProperty {

    private XyWzmMapperService mapperService;

    /**
     * MapperService 变量名称
     */
    private String mapperServiceVariable;

    /**
     * 分页查询对象类
     */
    private XyWzmPageListQo pageListQO;

    private XyWzmVo vo;

    private Po po;
}
