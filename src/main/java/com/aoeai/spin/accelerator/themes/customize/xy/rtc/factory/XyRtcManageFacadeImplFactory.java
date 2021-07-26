package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcManageFacadeImpl;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.config.XyRtcManageFacadeImplConfig;
import org.apache.commons.text.WordUtils;

/**
 * 门面控制器实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcManageFacadeImplFactory extends AbstractJavaFileFactory<XyRtcManageFacadeImpl> {

    private IPoFactory poFactory;
    private XyRtcManageFacadeFactory facadeFactory;
    private XyRtcManageServiceClassFactory serviceClassFactory;

    public XyRtcManageFacadeImplFactory(IPoFactory poFactory, XyRtcManageFacadeFactory facadeFactory,
                                        XyRtcManageServiceClassFactory serviceClassFactory) {
        this.poFactory = poFactory;
        this.facadeFactory = facadeFactory;
        this.serviceClassFactory = serviceClassFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcManageFacadeImpl build(String tableName) {
        return create(tableName, new XyRtcManageFacadeImpl());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/manage-facade-impl.yaml";
    }

    @Override
    protected IPoFactory poFactory() {
        return poFactory;
    }

    @Override
    protected Class<? extends JavaConfig> getConfigType() {
        return XyRtcManageFacadeImplConfig.class;
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
        var facade = facadeFactory.build(tableName);
        builder.setFacade(facade);
        builder.setForm(facade.getForm());
        builder.setPageListQO(facade.getPageListQO());

        var serviceClass = serviceClassFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));
    }

}
