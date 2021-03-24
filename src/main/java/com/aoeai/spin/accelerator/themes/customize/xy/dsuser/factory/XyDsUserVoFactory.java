package com.aoeai.spin.accelerator.themes.customize.xy.dsuser.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsVO;

/**
 * VO类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsUserVoFactory extends AbstractJavaFileFactory<XyDsVO> {

    private IPoFactory poFactory;

    public XyDsUserVoFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyDsVO build(String tableName) {
        return create(tableName, new XyDsVO());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dsuser/config/vo.yaml";
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
