package com.aoeai.spin.accelerator.themes.customize.xy.channel.factory;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChFacade;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChFacadeImpl;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 门面控制器实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChFacadeImplFactory extends AbstractJavaFileFactory<XyChFacadeImpl> {

    private IPoFactory poFactory;
    private XyChFacadeFactory xyChFacadeFactory;
    private XyChServiceClassFactory xyChServiceClassFactory;

    public XyChFacadeImplFactory(IPoFactory poFactory, XyChFacadeFactory xyChFacadeFactory, XyChServiceClassFactory xyChServiceClassFactory) {
        this.poFactory = poFactory;
        this.xyChFacadeFactory = xyChFacadeFactory;
        this.xyChServiceClassFactory = xyChServiceClassFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyChFacadeImpl build(String tableName) {
        return create(tableName, new XyChFacadeImpl());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel/config/facade-impl.yaml";
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
        XyChFacade facade = xyChFacadeFactory.build(tableName);
        builder.setFacade(facade);
        builder.setForm(facade.getForm());
        builder.setPageListQO(facade.getPageListQO());

        XyChServiceClass serviceClass = xyChServiceClassFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));
    }
}
