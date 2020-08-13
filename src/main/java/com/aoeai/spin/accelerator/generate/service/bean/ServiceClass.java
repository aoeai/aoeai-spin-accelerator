package com.aoeai.spin.accelerator.generate.service.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperService;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
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

    // 非必须
    private MapperService mapperService;

    /**
     * MapperService 变量名称
     */
    // 非必须
    private String mapperServiceVariable;

    /**
     * 分页查询对象类
     */
    private PageListQO pageListQO;

    private VO vo;

    private PO po;

    /**
     * 接口类（不需要时为null）
     */
    // 非必须
    private ServiceClass interfaceClass;

    // 非必须
    private MapperClass mapperClass;

    /**
     * MapperClass变量名称
     */
    // 非必须
    private String mapperClassVariable;

    /**
     * 主键字段
     */
    // 非必须
    private String pkColumn;

    /**
     * 设置主键值方法
     */
    // 非必须
    private String setPkMethod;

    /**
     * 获得主键值方法
     */
    // 非必须
    private String getPkMethod;
}
