package com.aoeai.spin.accelerator.themes.customize.xy.channel.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChServiceImplClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChServiceImplClassFactory extends AbstractJavaFileFactory<XyChServiceImplClass> {

    private IPoFactory poFactory;

    private XyChServiceClassFactory xyChServiceClassFactory;

    private IMapperFactory mapperFactory;

    public XyChServiceImplClassFactory(IPoFactory poFactory, XyChServiceClassFactory xyChServiceClassFactory, IMapperFactory mapperFactory) {
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
    public XyChServiceImplClass build(String tableName) {
        return create(tableName, new XyChServiceImplClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel/config/service-impl.yaml";
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
        var serviceClass = xyChServiceClassFactory.build(tableName);
        builder.setVo(serviceClass.getVo());
        builder.setPageListQO(serviceClass.getPageListQO());
        builder.setInterfaceClass(serviceClass);
        var po = poFactory.build(tableName);
        builder.setPo(po);
        var mapperClass = mapperFactory.build(tableName);
        builder.setMapperClass(mapperClass);
        builder.setMapperClassVariable(WordUtils.uncapitalize(mapperClass.getClassName()));

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
