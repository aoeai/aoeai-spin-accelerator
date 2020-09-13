package com.aoeai.spin.accelerator.themes.customize.xy.finance;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.factory.*;
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
public class XyFinanceThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyFinPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-finance");
        themeType.setName("星芽-财务");

        IMapperFactory mapperFactory = new XyFinMapperFactory(poFactory);
        XyFinVoFactory voFactory = new XyFinVoFactory(poFactory);
        XyFinFormFactory formFactory = new XyFinFormFactory(poFactory);
        XyFinPageListQoFactory pageListQoFactory = new XyFinPageListQoFactory(poFactory);
        XyFinServiceClassFactory serviceClassFactory = new XyFinServiceClassFactory(poFactory, voFactory, pageListQoFactory);
        XyFinProviderFactory providerFactory = new XyFinProviderFactory(poFactory, serviceClassFactory, mapperFactory);
        XyFinFacadeFactory facadeFactory = new XyFinFacadeFactory(poFactory, formFactory, pageListQoFactory);
        XyFinFacadeImplFactory facadeImplFactory = new XyFinFacadeImplFactory(poFactory, facadeFactory, serviceClassFactory);
        XyFinFacadeTestFactory facadeTestFactory = new XyFinFacadeTestFactory(poFactory, formFactory, pageListQoFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", new XyFinMapperXmlFactory(mapperFactory)),
                new Module("Provider", "服务实现", providerFactory),
                new Module("PageListQO", "分页查询对象", pageListQoFactory)
                /*,
                new Module("Service", "服务接口", serviceClassFactory),
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
