package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IMapperFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.generate1.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.XyWzmTools;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class XyWzmMapperFactory extends AbstractJavaFileFactory<MapperClass> implements IMapperFactory {

    private IPoFactory poFactory;

    public XyWzmMapperFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    @Override
    public MapperClass build(String tableName) {
        return create(tableName, new MapperClass());
    }

    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima1/config/mapper.yaml";
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

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
