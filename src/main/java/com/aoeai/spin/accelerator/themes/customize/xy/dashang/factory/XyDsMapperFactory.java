package com.aoeai.spin.accelerator.themes.customize.xy.dashang.factory;

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
public class XyDsMapperFactory extends AbstractJavaFileFactory<MapperClass> implements IMapperFactory {

    private IPoFactory poFactory;

    public XyDsMapperFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    @Override
    public MapperClass build(String tableName) {
        return create(tableName, new MapperClass());
    }

    @Override
    protected String configYaml() {
        return "/themes/xy/dashang/config/mapper.yaml";
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
