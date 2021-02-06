package com.aoeai.spin.accelerator.themes.customize.xy.media.factory;

import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaFacade;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaFacadeImpl;
import com.aoeai.spin.accelerator.themes.customize.xy.media.bean.XyMediaServiceClass;
import org.apache.commons.text.WordUtils;

/**
 * 门面控制器实现类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyMediaFacadeImplFactory extends AbstractJavaFileFactory<XyMediaFacadeImpl> {

    private IPoFactory poFactory;
    private XyMediaFacadeFactory xyChFacadeFactory;
    private XyMediaServiceClassFactory xyChServiceClassFactory;

    public XyMediaFacadeImplFactory(IPoFactory poFactory, XyMediaFacadeFactory xyChFacadeFactory, XyMediaServiceClassFactory xyChServiceClassFactory) {
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
    public XyMediaFacadeImpl build(String tableName) {
        return create(tableName, new XyMediaFacadeImpl());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/media/config/facade-impl.yaml";
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
        XyMediaFacade facade = xyChFacadeFactory.build(tableName);
        builder.setFacade(facade);
        builder.setForm(facade.getForm());
        builder.setPageListQO(facade.getPageListQO());

        XyMediaServiceClass serviceClass = xyChServiceClassFactory.build(tableName);
        builder.setServiceClass(serviceClass);
        builder.setServiceClassVariable(WordUtils.uncapitalize(serviceClass.getClassName()));
    }
}
