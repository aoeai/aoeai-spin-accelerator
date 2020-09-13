package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinFacade;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinFacadeImpl;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 门面控制器实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyFinFacadeImplFactory extends AbstractJavaFileFactory<XyFinFacadeImpl> {

    private IPoFactory poFactory;
    private XyFinFacadeFactory xyChFacadeFactory;
    private XyFinServiceClassFactory xyChServiceClassFactory;

    public XyFinFacadeImplFactory(IPoFactory poFactory, XyFinFacadeFactory xyChFacadeFactory, XyFinServiceClassFactory xyChServiceClassFactory) {
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
    public XyFinFacadeImpl build(String tableName) {
        return create(tableName, new XyFinFacadeImpl());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/finance/config/facade-impl.yaml";
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
        XyFinFacade facade = xyChFacadeFactory.build(tableName);
        builder.setFacade(facade);
        builder.setForm(facade.getForm());
        builder.setPageListQO(facade.getPageListQO());

        XyFinServiceClass serviceClass = xyChServiceClassFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));
    }
}
