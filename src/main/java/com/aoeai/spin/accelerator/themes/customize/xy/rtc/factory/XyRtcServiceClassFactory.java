package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcServiceClass;

/**
 * 服务接口类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcServiceClassFactory extends AbstractJavaFileFactory<XyRtcServiceClass> {

    private IPoFactory poFactory;

    private XyRtcVoFactory voFactory;

    private XyRtcPageListQoFactory pageListQoFactory;

    public XyRtcServiceClassFactory(IPoFactory poFactory, XyRtcVoFactory voFactory, XyRtcPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.voFactory = voFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcServiceClass build(String tableName) {
        return create(tableName, new XyRtcServiceClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/service.yaml";
    }

    /**
     * Po工厂
     *
     * @return
     */
    @Override
    protected IPoFactory poFactory() {
        return poFactory;
    }

    /**
     * 手动创建逻辑
     *
     * @param tableName
     */
    @Override
    protected void manualCreate(String tableName) {
        Po po = poFactory.build(tableName);
        builder.setPo(po);
        builder.setPageListQO(pageListQoFactory.build(tableName));
        builder.setVo(voFactory.build(tableName));
    }
}
