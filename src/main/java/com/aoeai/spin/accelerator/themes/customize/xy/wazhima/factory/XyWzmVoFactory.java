package com.aoeai.spin.accelerator.themes.customize.xy.wazhima.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.bean.XyWzmVo;

/**
 * MapperService类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmVoFactory extends AbstractJavaFileFactory<XyWzmVo> {

    private IPoFactory poFactory;

    public XyWzmVoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmVo build(String tableName) {
        return create(tableName, new XyWzmVo());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima/config/vo.yaml";
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

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
