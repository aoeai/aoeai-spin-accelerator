package com.aoeai.spin.accelerator.themes.customize.xy.channel;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.factory.XyChMapperFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.factory.XyChMapperXmlFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-渠道模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyChannelThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyChPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-channel");
        themeType.setName("星芽-渠道");

        IMapperFactory mapperFactory = new XyChMapperFactory(poFactory);
        XyChVoFactory voFactory = new XyChVoFactory(poFactory);
        XyChFormFactory formFactory = new XyChFormFactory(poFactory);
        XyChPageListQoFactory pageListQoFactory = new XyChPageListQoFactory(poFactory);
        XyChServiceClassFactory serviceClassFactory = new XyChServiceClassFactory(poFactory, voFactory, pageListQoFactory);
        XyChServiceImplClassFactory serviceImplClassFactory = new XyChServiceImplClassFactory(poFactory, serviceClassFactory, mapperFactory);
        XyChFacadeFactory facadeFactory = new XyChFacadeFactory(poFactory, formFactory, pageListQoFactory);
        XyChFacadeImplFactory facadeImplFactory = new XyChFacadeImplFactory(poFactory, facadeFactory, serviceClassFactory);
        XyChFacadeTestFactory facadeTestFactory = new XyChFacadeTestFactory(poFactory, formFactory, pageListQoFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", new XyChMapperXmlFactory(mapperFactory)),
                new Module("Service", "服务接口", serviceClassFactory),
                new Module("ServiceImpl", "服务实现", serviceImplClassFactory),
                new Module("Facade", "门面控制器接口", facadeFactory),
                new Module("FacadeImpl", "门面控制器实现类", facadeImplFactory),
                new Module("VO", "Web层返回对象", voFactory),
                new Module("Form", "表单", formFactory),
                new Module("PageListQO", "分页查询对象", pageListQoFactory),
                new Module("facadeTest", "门面控制器测试类", facadeTestFactory)
        );

        themeType.setModules(modules);
        return themeType;
    }

}
