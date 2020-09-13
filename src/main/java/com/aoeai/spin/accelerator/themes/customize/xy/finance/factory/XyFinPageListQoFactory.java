package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinPageListQo;

/**
 * 分页查询对象工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyFinPageListQoFactory extends AbstractJavaFileFactory<XyFinPageListQo> {

    private IPoFactory poFactory;

    public XyFinPageListQoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyFinPageListQo build(String tableName) {
        return create(tableName, new XyFinPageListQo());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/finance/config/page-list-qo.yaml";
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
