package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class XyRtcManageMapperFactory extends AbstractJavaFileFactory<MapperClass> implements IMapperFactory {

    private IPoFactory poFactory;

    public XyRtcManageMapperFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    @Override
    public MapperClass build(String tableName) {
        var clazz = create(tableName, new MapperClass());
        clazz.setTemplates("xy/rtc/manage_mapper.ftl");
        return clazz;
    }

    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/manage-mapper.yaml";
    }

    @Override
    protected IPoFactory poFactory() {
        return poFactory;
    }

    @Override
    protected void manualCreate(String tableName) {
        Po po = poFactory.build(tableName);
        builder.setPo(po);
        JavaConfig cfg = ConfigTools.getConfig(configYaml(), JavaConfig.class);
        builder.setClassName(po.getClassNameWithoutSuffix() + cfg.getSuffix());
        builder.setClassComment(po.getTable().getComment() + " Mapper");
    }
}
