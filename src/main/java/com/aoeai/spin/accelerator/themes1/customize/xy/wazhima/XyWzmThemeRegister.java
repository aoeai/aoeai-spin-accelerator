package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima;

import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory.*;
import com.aoeai.spin.accelerator.themes1.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes1.frame.bean.Module;
import com.aoeai.spin.accelerator.themes1.frame.bean.ThemeType;
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

        XyWzmMapperFactory mapperFactory = new XyWzmMapperFactory(poFactory);
        XyWzmMapperXmlFactory mapperXmlFactory = new XyWzmMapperXmlFactory(mapperFactory);
        XyWzmMapperServiceFactory mapperServiceFactory = new XyWzmMapperServiceFactory(poFactory);
        XyWzmMapperServiceImplFactory mapperServiceImplFactory = new XyWzmMapperServiceImplFactory(poFactory,
                mapperServiceFactory, mapperFactory);
        XyWzmVoFactory voFactory = new XyWzmVoFactory(poFactory);
        XyWzmPageListQoFactory pageListQoFactory = new XyWzmPageListQoFactory(poFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", mapperXmlFactory),
                new Module("MapperService", "Mybatis Mapper Service", mapperServiceFactory),
                new Module("MapperServiceImpl", "Mybatis Mapper Service Impl", mapperServiceImplFactory),
                new Module("Vo", "Web层返回对象", voFactory),
                new Module("PageListQo", "分页查询对象", pageListQoFactory)

        );

        themeType.setModules(modules);
        return themeType;
    }

}
