package com.aoeai.spin.accelerator.themes.customize.xy.channel.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChVO;

/**
 * VO类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChVoFactory extends AbstractJavaFileFactory<XyChVO> {

    private IPoFactory poFactory;

    public XyChVoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyChVO build(String tableName) {
        return create(tableName, new XyChVO());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel/config/vo.yaml";
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
        var po = poFactory.build(tableName);
        builder.setPo(po);
    }
}
