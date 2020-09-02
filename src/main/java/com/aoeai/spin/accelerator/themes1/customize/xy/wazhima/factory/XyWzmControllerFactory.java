package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean.XyWzmController;
import org.apache.commons.text.WordUtils;

/**
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmControllerFactory extends AbstractJavaFileFactory<XyWzmController> {

    private IPoFactory poFactory;
    private XyWzmServiceFactory serviceFactory;
    private XyWzmPageListQoFactory pageListQoFactory;
    private XyWzmFormFactory formFactory;

    public XyWzmControllerFactory(IPoFactory poFactory, XyWzmServiceFactory serviceFactory,
                                  XyWzmPageListQoFactory pageListQoFactory, XyWzmFormFactory formFactory) {
        this.poFactory = poFactory;
        this.serviceFactory = serviceFactory;
        this.pageListQoFactory = pageListQoFactory;
        this.formFactory = formFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmController build(String tableName) {
        return create(tableName, new XyWzmController());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima1/config/controller.yaml";
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
        builder.setServiceClass(serviceFactory.build(tableName));
        builder.setServiceClassVariable(WordUtils.uncapitalize(builder.getServiceClass().getClassName()));
        builder.setClassComment(po.getClassComment() + "控制器");
        builder.setPageListQO(pageListQoFactory.build(tableName));
        builder.setForm(formFactory.build(tableName));

        String urlPrefix = ClassTools.getModelName(po.getTable().getName(), po.getTablePrefixFilter());
        builder.setUrlPrefix(urlPrefix);
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
