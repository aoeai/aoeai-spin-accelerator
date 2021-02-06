package com.aoeai.spin.accelerator.themes.customize.xy.media.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaPageListQo;

/**
 * 分页查询对象工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyMediaPageListQoFactory extends AbstractJavaFileFactory<XyMediaPageListQo> {

    private IPoFactory poFactory;

    public XyMediaPageListQoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyMediaPageListQo build(String tableName) {
        return create(tableName, new XyMediaPageListQo());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/media/config/page-list-qo.yaml";
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
