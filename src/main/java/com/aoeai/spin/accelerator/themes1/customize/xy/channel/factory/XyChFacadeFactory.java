package com.aoeai.spin.accelerator.themes1.customize.xy.channel.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.themes1.customize.xy.channel.bean.XyChFacade;

/**
 * 门面控制器接口工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChFacadeFactory extends AbstractJavaFileFactory<XyChFacade> {

    private IPoFactory poFactory;
    private XyChFormFactory formFactory;
    private XyChPageListQoFactory pageListQoFactory;

    public XyChFacadeFactory(IPoFactory poFactory, XyChFormFactory formFactory, XyChPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.formFactory = formFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyChFacade build(String tableName) {
        return create(tableName, new XyChFacade());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel1/config/facade.yaml";
    }

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
        builder.setForm(formFactory.build(tableName));
        builder.setPageListQO(pageListQoFactory.build(tableName));
    }
}
