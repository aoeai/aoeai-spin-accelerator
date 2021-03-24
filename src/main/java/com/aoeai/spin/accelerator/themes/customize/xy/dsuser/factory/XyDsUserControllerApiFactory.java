package com.aoeai.spin.accelerator.themes.customize.xy.dsuser.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsController;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 控制器实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsUserControllerApiFactory extends AbstractJavaFileFactory<XyDsController> {

    private IPoFactory poFactory;
    private XyDsUserServiceFactory serviceFactory;
    private XyDsUserFormCreateFactory formCreateFactory;
    private XyDsUserFormUpdateFactory formUpdateFactory;
    private XyDsUserPageListQoFactory pageListQoFactory;

    public XyDsUserControllerApiFactory(IPoFactory poFactory, XyDsUserServiceFactory serviceFactory,
                                        XyDsUserFormCreateFactory formCreateFactory, XyDsUserFormUpdateFactory formUpdateFactory,
                                        XyDsUserPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.serviceFactory = serviceFactory;
        this.formCreateFactory = formCreateFactory;
        this.formUpdateFactory = formUpdateFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyDsController build(String tableName) {
        return create(tableName, new XyDsController());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dsuser/config/controller-api.yaml";
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
        builder.setFormCreate(formCreateFactory.build(tableName));
        builder.setFormUpdate(formUpdateFactory.build(tableName));
        builder.setPageListQO(pageListQoFactory.build(tableName));

        XyDsServiceClass serviceClass = serviceFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));
        builder.setPathName(WordUtils.uncapitalize(po.getClassNameWithoutSuffix()));
    }
}
