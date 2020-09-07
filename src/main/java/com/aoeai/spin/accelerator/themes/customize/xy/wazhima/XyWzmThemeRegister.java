package com.aoeai.spin.accelerator.themes.customize.xy.wazhima;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-挖芝麻模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyWzmThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyWzmPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-wazhima");
        themeType.setName("星芽-挖芝麻");

        IMapperFactory mapperFactory = new XyWzmMapperFactory(poFactory);
        XyWzmMapperXmlFactory mapperXmlFactory = new XyWzmMapperXmlFactory(mapperFactory);
        XyWzmMapperServiceFactory mapperServiceFactory = new XyWzmMapperServiceFactory(poFactory);
        XyWzmMapperServiceImplFactory mapperServiceImplFactory = new XyWzmMapperServiceImplFactory(poFactory,
                mapperServiceFactory, mapperFactory);
        XyWzmVoFactory voFactory = new XyWzmVoFactory(poFactory);
        XyWzmPageListQoFactory pageListQoFactory = new XyWzmPageListQoFactory(poFactory);
        XyWzmFormFactory formFactory = new XyWzmFormFactory(poFactory);
        XyWzmServiceFactory serviceFactory = new XyWzmServiceFactory(poFactory, mapperServiceFactory, voFactory,
                pageListQoFactory);
        XyWzmControllerFactory controllerFactory = new XyWzmControllerFactory(poFactory, serviceFactory,
                pageListQoFactory, formFactory);
        XyWzmControllerTestFactory controllerTestFactory = new XyWzmControllerTestFactory(poFactory, pageListQoFactory,
                formFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", mapperXmlFactory),
                new Module("MapperService", "Mybatis Mapper Service", mapperServiceFactory),
                new Module("MapperServiceImpl", "Mybatis Mapper Service Impl", mapperServiceImplFactory),
                new Module("Service", "业务逻辑服务", serviceFactory),
                new Module("Controller", "控制器", controllerFactory),
                new Module("Vo", "Web层返回对象", voFactory),
                new Module("PageListQo", "分页查询对象", pageListQoFactory),
                new Module("Form", "表单", formFactory),
                new Module("ControllerTest", "控制器测试", controllerTestFactory)

        );

        themeType.setModules(modules);
        return themeType;
    }

}
