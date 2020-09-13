package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinServiceClass;

/**
 * 服务接口类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyFinServiceClassFactory extends AbstractJavaFileFactory<XyFinServiceClass> {

    private IPoFactory poFactory;

    private XyFinVoFactory xyChVoFactory;

    private XyFinPageListQoFactory xyChPageListQoFactory;

    public XyFinServiceClassFactory(IPoFactory poFactory, XyFinVoFactory xyChVoFactory, XyFinPageListQoFactory xyChPageListQoFactory) {
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
    public XyFinServiceClass build(String tableName) {
        return create(tableName, new XyFinServiceClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/finance/config/service.yaml";
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
