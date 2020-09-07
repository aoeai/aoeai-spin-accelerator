package com.aoeai.spin.accelerator.themes.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.bean.XyWzmPageListQo;

/**
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmPageListQoFactory extends AbstractJavaFileFactory<XyWzmPageListQo> {

    private IPoFactory poFactory;

    public XyWzmPageListQoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmPageListQo build(String tableName) {
        return create(tableName, new XyWzmPageListQo());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima/config/page-list-qo.yaml";
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
        builder.setClassComment(po.getClassComment() + "分页查询条件");
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
