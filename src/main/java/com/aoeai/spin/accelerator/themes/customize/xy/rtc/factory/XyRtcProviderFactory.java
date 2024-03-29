package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcProviderClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcProviderFactory extends AbstractJavaFileFactory<XyRtcProviderClass> {

    private IPoFactory poFactory;

    private XyRtcServiceClassFactory serviceClassFactory;

    private IMapperFactory mapperFactory;

    private XyRtcPageListQoFactory pageListQoFactory;

    public XyRtcProviderFactory(IPoFactory poFactory, IMapperFactory mapperFactory, XyRtcPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.serviceClassFactory = serviceClassFactory;
        this.mapperFactory = mapperFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcProviderClass build(String tableName) {
        var provider = create(tableName, new XyRtcProviderClass());
        provider.setTemplates("xy/rtc/provider.ftl");
        return provider;
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/provider.yaml";
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
        builder.setPageListQO(pageListQoFactory.build(tableName));
        Po po = poFactory.build(tableName);
        builder.setPo(po);
        MapperClass mapperClass = mapperFactory.build(tableName);
        builder.setMapperClass(mapperClass);
        builder.setMapperClassVariable(WordUtils.uncapitalize(mapperClass.getClassName()));

        String pkColumn = "";
        for (POField field : po.getFieldList()) {
            if (field.getIsPrimaryKey()) {
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
