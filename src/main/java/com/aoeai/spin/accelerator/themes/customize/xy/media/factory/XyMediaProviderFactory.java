package com.aoeai.spin.accelerator.themes.customize.xy.media.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaServiceClass;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaServiceImplClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyMediaProviderFactory extends AbstractJavaFileFactory<XyMediaServiceImplClass> {

    private IPoFactory poFactory;

    private XyMediaServiceClassFactory xyChServiceClassFactory;

    private IMapperFactory mapperFactory;

    public XyMediaProviderFactory(IPoFactory poFactory,
                                  XyMediaServiceClassFactory xyChServiceClassFactory,
                                  IMapperFactory mapperFactory) {
        this.poFactory = poFactory;
        this.xyChServiceClassFactory = xyChServiceClassFactory;
        this.mapperFactory = mapperFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyMediaServiceImplClass build(String tableName) {
        return create(tableName, new XyMediaServiceImplClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/media/config/provider.yaml";
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
        XyMediaServiceClass serviceClass = xyChServiceClassFactory.build(tableName);
        builder.setVo(serviceClass.getVo());
        builder.setPageListQO(serviceClass.getPageListQO());
        builder.setInterfaceClass(serviceClass);
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
