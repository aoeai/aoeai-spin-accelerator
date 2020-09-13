package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinFacade;

/**
 * 门面控制器接口工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyFinFacadeFactory extends AbstractJavaFileFactory<XyFinFacade> {

    private IPoFactory poFactory;
    private XyFinFormFactory formFactory;
    private XyFinPageListQoFactory pageListQoFactory;

    public XyFinFacadeFactory(IPoFactory poFactory, XyFinFormFactory formFactory, XyFinPageListQoFactory pageListQoFactory) {
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
    public XyFinFacade build(String tableName) {
        return create(tableName, new XyFinFacade());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/finance/config/facade.yaml";
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
