package com.aoeai.spin.accelerator.themes.customize.xy.media.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaServiceClass;

/**
 * 服务接口类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyMediaServiceClassFactory extends AbstractJavaFileFactory<XyMediaServiceClass> {

    private IPoFactory poFactory;

    private XyMediaVoFactory xyChVoFactory;

    private XyMediaPageListQoFactory xyChPageListQoFactory;

    public XyMediaServiceClassFactory(IPoFactory poFactory, XyMediaVoFactory xyChVoFactory, XyMediaPageListQoFactory xyChPageListQoFactory) {
        this.poFactory = poFactory;
        this.xyChVoFactory = xyChVoFactory;
        this.xyChPageListQoFactory = xyChPageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyMediaServiceClass build(String tableName) {
        return create(tableName, new XyMediaServiceClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/media/config/service.yaml";
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
        builder.setPageListQO(xyChPageListQoFactory.build(tableName));
        builder.setVo(xyChVoFactory.build(tableName));
    }
}
