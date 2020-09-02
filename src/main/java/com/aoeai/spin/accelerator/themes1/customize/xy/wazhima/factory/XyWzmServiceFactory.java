package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean.XyWzmServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmServiceFactory extends AbstractJavaFileFactory<XyWzmServiceClass> {

    private IPoFactory poFactory;
    private XyWzmMapperServiceFactory mapperServiceFactory;
    private XyWzmVoFactory voFactory;
    private XyWzmPageListQoFactory pageListQoFactory;

    public XyWzmServiceFactory(IPoFactory poFactory, XyWzmMapperServiceFactory mapperServiceFactory,
                               XyWzmVoFactory voFactory, XyWzmPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.mapperServiceFactory = mapperServiceFactory;
        this.voFactory = voFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmServiceClass build(String tableName) {
        return create(tableName, new XyWzmServiceClass());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima1/config/service.yaml";
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
        builder.setMapperService(mapperServiceFactory.build(tableName));
        builder.setMapperServiceVariable(WordUtils.uncapitalize(builder.getMapperService().getClassName()));
        builder.setClassComment(po.getClassComment() + "服务");
        builder.setPageListQO(pageListQoFactory.build(tableName));
        builder.setVo(voFactory.build(tableName));
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
