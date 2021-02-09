package com.aoeai.spin.accelerator.themes.customize.xy.dashang;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-达赏模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyDashangThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyDsPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-dashang");
        themeType.setName("星芽-达赏");

        IMapperFactory mapperFactory = new XyDsMapperFactory(poFactory);
        XyDsVoFactory voFactory = new XyDsVoFactory(poFactory);
        XyDsPageListQoFactory pageListQoFactory = new XyDsPageListQoFactory(poFactory);
        XyDsServiceFactory serviceFactory = new XyDsServiceFactory(poFactory, mapperFactory, voFactory, pageListQoFactory);
        XyDsFormCreateFactory formCreateFactory = new XyDsFormCreateFactory(poFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", new XyDsMapperXmlFactory(mapperFactory)),
                new Module("VO", "Web层返回对象", voFactory),
                new Module("PageListQO", "分页查询对象", pageListQoFactory),
                new Module("Service", "服务实现", serviceFactory),
                new Module("FormCreate", "创建表单", formCreateFactory)

                /*,
                ,
                new Module("Service", "服务接口", serviceClassFactory),
                new Module("Facade", "门面控制器接口", facadeFactory),
                new Module("FacadeImpl", "门面控制器实现类", facadeImplFactory),
                ,
                new Module("facadeTest", "门面控制器测试类", facadeTestFactory)*/
        );

        themeType.setModules(modules);
        return themeType;
    }

}
