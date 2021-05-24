package com.aoeai.spin.accelerator.themes.customize.xy.channel.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChServiceClass;

/**
 * 服务接口类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChServiceClassFactory extends AbstractJavaFileFactory<XyChServiceClass> {

    private IPoFactory poFactory;

    private XyChVoFactory xyChVoFactory;

    private XyChPageListQoFactory xyChPageListQoFactory;

    public XyChServiceClassFactory(IPoFactory poFactory, XyChVoFactory xyChVoFactory, XyChPageListQoFactory xyChPageListQoFactory) {
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
    public XyChServiceClass build(String tableName) {
        return create(tableName, new XyChServiceClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel/config/service.yaml";
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
        var po = poFactory.build(tableName);
        builder.setPo(po);
        builder.setPageListQO(xyChPageListQoFactory.build(tableName));
        builder.setVo(xyChVoFactory.build(tableName));
    }
}
