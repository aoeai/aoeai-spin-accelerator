package com.aoeai.spin.accelerator.themes.customize.xy.dsuser;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.dsuser.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-达赏-用户模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyDsUserThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyDsUserPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-ds-user");
        themeType.setName("星芽-达赏-用户");

        IMapperFactory mapperFactory = new XyDsUserMapperFactory(poFactory);
        XyDsUserVoFactory voFactory = new XyDsUserVoFactory(poFactory);
        XyDsUserPageListQoFactory pageListQoFactory = new XyDsUserPageListQoFactory(poFactory);
        XyDsUserServiceFactory serviceFactory = new XyDsUserServiceFactory(poFactory, mapperFactory, voFactory, pageListQoFactory);
        XyDsUserFormCreateFactory formCreateFactory = new XyDsUserFormCreateFactory(poFactory);
        XyDsUserFormUpdateFactory formUpdateFactory = new XyDsUserFormUpdateFactory(poFactory);
        XyDsUserControllerApiFactory controllerApiFactory = new XyDsUserControllerApiFactory(poFactory, serviceFactory, formCreateFactory,
                formUpdateFactory, pageListQoFactory);
        XyDsUserControllerAppFactory controllerAppFactory = new XyDsUserControllerAppFactory(poFactory, serviceFactory, formCreateFactory,
                formUpdateFactory, pageListQoFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", new XyDsUserMapperXmlFactory(mapperFactory)),
                new Module("MapperExtXml", "Mybatis Ext Mapper MXL", new XyDsUserMapperExtXmlFactory(mapperFactory)),
                new Module("VO", "Web层返回对象", voFactory),
                new Module("PageListQuery", "分页查询对象", pageListQoFactory),
                new Module("Service", "服务实现", serviceFactory),
                new Module("FormCreate", "创建表单", formCreateFactory),
                new Module("FormUpdate", "更新表单", formUpdateFactory),
                new Module("ControllerApi", "Api控制器", controllerApiFactory),
                new Module("ControllerApp", "App控制器", controllerAppFactory)
        );

        themeType.setModules(modules);
        return themeType;
    }

}
