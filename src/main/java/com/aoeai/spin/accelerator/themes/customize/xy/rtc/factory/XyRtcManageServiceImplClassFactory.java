package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcManageServiceImplClass;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcManageServiceImplClassFactory extends AbstractJavaFileFactory<XyRtcManageServiceImplClass> {

    private IPoFactory poFactory;

    private XyRtcManageProviderFactory manageProviderFactory;

    private XyRtcServiceClassFactory serviceClassFactory;

    private XyRtcManageServiceClassFactory manageServiceClassFactory;

    public XyRtcManageServiceImplClassFactory(IPoFactory poFactory,
                                              XyRtcManageProviderFactory manageProviderFactory,
                                              XyRtcServiceClassFactory serviceClassFactory,
                                              XyRtcManageServiceClassFactory manageServiceClassFactory) {
        this.poFactory = poFactory;
        this.manageProviderFactory = manageProviderFactory;
        this.serviceClassFactory = serviceClassFactory;
        this.manageServiceClassFactory = manageServiceClassFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcManageServiceImplClass build(String tableName) {
        var clazz = create(tableName, new XyRtcManageServiceImplClass());
        clazz.setTemplates("xy/rtc/manage_service_impl.ftl");
        return clazz;
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/manage-service-impl.yaml";
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
        var manageProvider = manageProviderFactory.build(tableName);
        builder.setPageListQO(manageProvider.getPageListQO());
        var po = poFactory.build(tableName);
        builder.setPo(po);
        builder.setManageProviderClass(manageProvider);
        builder.setManageProviderVariable(WordUtils.uncapitalize(manageProvider.getClassName()));

        XyRtcServiceClass serviceClass = serviceClassFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));

        builder.setInterfaceClass(manageServiceClassFactory.build(tableName));

        var pkColumn = "";
        for (POField field : po.getFieldList()) {
            if (Boolean.TRUE.equals(field.getIsPrimaryKey())) {
                pkColumn = field.getName();
                break;
            }
        }
        builder.setPkColumn(pkColumn);
        String pk = WordUtils.capitalize(pkColumn);
        builder.setSetPkMethod("set" + pk);
        builder.setGetPkMethod("get" + pk);
    }
}
