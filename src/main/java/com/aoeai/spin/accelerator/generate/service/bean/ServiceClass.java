package com.aoeai.spin.accelerator.generate.service.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperService;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import lombok.Data;

/**
 * 服务类
 * @author aoe
 * @date 2020/6/22
 */
@Data
public class ServiceClass extends BaseClassProperty {

    private MapperService mapperService;

    /**
     * MapperService 变量名称
     */
    private String mapperServiceVariable;

    /**
     * 分页查询对象类
     */
    private PageListQO pageListQO;

    /**
     *
     */
    private VO vo;
}
