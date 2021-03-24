package com.aoeai.spin.accelerator.themes.customize.xy.dsuser.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 服务实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsUserServiceFactory extends AbstractJavaFileFactory<XyDsServiceClass> {

    private IPoFactory poFactory;

    private IMapperFactory mapperFactory;

    private XyDsUserVoFactory voFactory;

    private XyDsUserPageListQoFactory pageListQoFactory;

    public XyDsUserServiceFactory(IPoFactory poFactory, IMapperFactory mapperFactory,
                                  XyDsUserVoFactory voFactory, XyDsUserPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.mapperFactory = mapperFactory;
        this.voFactory = voFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dsuser/config/service.yaml";
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

    @Override
    public XyDsServiceClass build(String tableName) {
        return create(tableName, new XyDsServiceClass());
    }

    /**
     * 手动创建逻辑
     *
     * @param tableName
     */
    @Override
    protected void manualCreate(String tableName) {
        builder.setVo(voFactory.build(tableName));
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
        builder.setGetPkMethod("get" + pk);
    }

}
