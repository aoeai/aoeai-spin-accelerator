package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean.XyWzmMapperServiceImpl;

/**
 * MapperService类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmMapperServiceImplFactory extends AbstractJavaFileFactory<XyWzmMapperServiceImpl> {

    private IPoFactory poFactory;

    private XyWzmMapperServiceFactory mapperServiceFactory;

    private XyWzmMapperFactory mapperFactory;

    public XyWzmMapperServiceImplFactory(IPoFactory poFactory, XyWzmMapperServiceFactory mapperServiceFactory,
                                         XyWzmMapperFactory mapperFactory) {
        this.poFactory = poFactory;
        this.mapperServiceFactory = mapperServiceFactory;
        this.mapperFactory = mapperFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmMapperServiceImpl build(String tableName) {
        return create(tableName, new XyWzmMapperServiceImpl());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima1/config/mapper-service-impl.yaml";
    }

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
        Po po = poFactory.build(tableName);
        builder.setPo(po);

        builder.setClassComment(po.getTable().getComment() + "服务类");
        builder.setMapperService(mapperServiceFactory.build(tableName));
        builder.setMapperClass(mapperFactory.build(tableName));
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
