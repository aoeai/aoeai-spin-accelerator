package com.aoeai.spin.accelerator.themes.customize.xy.dashang.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsPageListQo;

/**
 * 分页查询对象工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsPageListQoFactory extends AbstractJavaFileFactory<XyDsPageListQo> {

    private IPoFactory poFactory;

    public XyDsPageListQoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyDsPageListQo build(String tableName) {
        return create(tableName, new XyDsPageListQo());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dashang/config/page-list-qo.yaml";
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
    }
}
