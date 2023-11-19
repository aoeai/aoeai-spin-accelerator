package com.aoeai.spin.accelerator.themes.customize.qm.kuyin.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QmKuyinManagePageParamFactory extends AbstractJavaFileFactory<MapperClass> implements IMapperFactory {

    private IPoFactory poFactory;

    @Override
    public MapperClass build(String tableName) {
        var mapperClass = create(tableName, new MapperClass());
        mapperClass.setTemplates("qm/kuyin/manage_page_param.ftl");
        return mapperClass;
    }

    @Override
    protected String configYaml() {
        return "/themes/qm/kuyin/config/manage_page_param.yaml";
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
        builder.setClassComment(po.getTable().getComment() + "分页查询参数");
    }
}
