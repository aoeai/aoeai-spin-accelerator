package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcManageFacade;

/**
 * 门面控制器接口工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcManageFacadeFactory extends AbstractJavaFileFactory<XyRtcManageFacade> {

    private IPoFactory poFactory;
    private XyRtcManageFormFactory formFactory;
    private XyRtcPageListQoFactory pageListQoFactory;

    public XyRtcManageFacadeFactory(IPoFactory poFactory, XyRtcManageFormFactory formFactory,
                                    XyRtcPageListQoFactory pageListQoFactory) {
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
    public XyRtcManageFacade build(String tableName) {
        return create(tableName, new XyRtcManageFacade());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/manage-facade.yaml";
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
        var po = poFactory.build(tableName);
        builder.setPo(po);
        builder.setForm(formFactory.build(tableName));
        builder.setPageListQO(pageListQoFactory.build(tableName));
    }
}
