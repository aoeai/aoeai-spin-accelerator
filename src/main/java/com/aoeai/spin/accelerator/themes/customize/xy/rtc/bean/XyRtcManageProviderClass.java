package com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory.XyRtcManageProviderFactory;
import lombok.Data;

/**
 * @author aoe
 * @date 2021/7/23
 */
@Data
public class XyRtcManageProviderClass extends BaseClassProperty {

    /**
     * 分页查询对象类
     */
    private XyRtcPageListQo pageListQO;

    private Po po;

    private XyRtcManageProviderFactory manageProviderFactory;

    private MapperClass mapperClass;

    /**
     * MapperClass变量名称
     */
    private String mapperClassVariable;

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
