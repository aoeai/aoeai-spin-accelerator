package com.aoeai.spin.accelerator.themes1.customize.xy.channel.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import lombok.Data;

/**
 * 服务实现类
 * @author aoe
 * @date 2020/6/22
 */
@Data
public class XyChServiceImplClass extends BaseClassProperty {

    /**
     * 分页查询对象类
     */
    private XyChPageListQo pageListQO;

    private XyChVO vo;

    private Po po;

    private MapperClass mapperClass;

    /**
     * MapperClass变量名称
     */
    private String mapperClassVariable;

    private XyChServiceClass interfaceClass;

    /**
     * 主键字段
     */
    private String pkColumn;

    /**
     * 设置主键值方法
     */
    private String setPkMethod;

    /**
     * 获得主键值方法
     */
    private String getPkMethod;
}
