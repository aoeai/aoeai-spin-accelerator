package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcServiceImplClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcServiceImplClassFactory extends AbstractJavaFileFactory<XyRtcServiceImplClass> {

    private IPoFactory poFactory;

    private XyRtcServiceClassFactory serviceClassFactory;

    private XyRtcProviderFactory providerFactory;

    public XyRtcServiceImplClassFactory(IPoFactory poFactory, XyRtcServiceClassFactory serviceClassFactory, XyRtcProviderFactory providerFactory) {
        this.poFactory = poFactory;
        this.serviceClassFactory = serviceClassFactory;
        this.providerFactory = providerFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcServiceImplClass build(String tableName) {
        var clazz = create(tableName, new XyRtcServiceImplClass());
        clazz.setTemplates("xy/rtc/service_impl.ftl");
        return clazz;
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/service-impl.yaml";
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
        var serviceClass = serviceClassFactory.build(tableName);
        builder.setVo(serviceClass.getVo());
        builder.setPageListQO(serviceClass.getPageListQO());
        builder.setInterfaceClass(serviceClass);
        var po = poFactory.build(tableName);
        builder.setPo(po);
        var providerClass = providerFactory.build(tableName);
        builder.setProviderClass(providerClass);
        builder.setProviderClassVariable(WordUtils.uncapitalize(providerClass.getClassName()));

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
