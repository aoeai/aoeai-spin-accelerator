package com.aoeai.spin.accelerator.themes.customize.xy.media;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.media.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-财务模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyMediaThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyMediaPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-media");
        themeType.setName("星芽-媒体");

        IMapperFactory mapperFactory = new XyMediaMapperFactory(poFactory);
        XyMediaVoFactory voFactory = new XyMediaVoFactory(poFactory);
        XyMediaFormFactory formFactory = new XyMediaFormFactory(poFactory);
        XyMediaPageListQoFactory pageListQoFactory = new XyMediaPageListQoFactory(poFactory);
        XyMediaServiceClassFactory serviceClassFactory = new XyMediaServiceClassFactory(poFactory, voFactory, pageListQoFactory);
        XyMediaProviderFactory providerFactory = new XyMediaProviderFactory(poFactory, serviceClassFactory, mapperFactory);
        XyMediaFacadeFactory facadeFactory = new XyMediaFacadeFactory(poFactory, formFactory, pageListQoFactory);
        XyMediaFacadeImplFactory facadeImplFactory = new XyMediaFacadeImplFactory(poFactory, facadeFactory, serviceClassFactory);
        XyMediaFacadeTestFactory facadeTestFactory = new XyMediaFacadeTestFactory(poFactory, formFactory, pageListQoFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", new XyMediaMapperXmlFactory(mapperFactory)),
                new Module("Provider", "服务实现", providerFactory),
                new Module("PageListQO", "分页查询对象", pageListQoFactory),
                new Module("Service", "服务接口", serviceClassFactory)
                /*,
                new Module("Facade", "门面控制器接口", facadeFactory),
                new Module("FacadeImpl", "门面控制器实现类", facadeImplFactory),
                new Module("VO", "Web层返回对象", voFactory),
                new Module("Form", "表单", formFactory),
                new Module("facadeTest", "门面控制器测试类", facadeTestFactory)*/
        );

        themeType.setModules(modules);
        return themeType;
    }

}
