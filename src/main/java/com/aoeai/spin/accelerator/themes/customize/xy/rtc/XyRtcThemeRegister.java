package com.aoeai.spin.accelerator.themes.customize.xy.rtc;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory.*;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 星芽-语音房模板注册
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyRtcThemeRegister extends AbstractThemeRegister {

    @Resource(name = "xyRtcPoFactory")
    private IPoFactory poFactory;

    /**
     * 获得模板类型
     *
     * @return
     */
    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("xy-rtc");
        themeType.setName("星芽-语音房");

        IMapperFactory mapperFactory = new XyRtcMapperFactory(poFactory);
        XyRtcMapperXmlFactory mapperXmlFactory = new XyRtcMapperXmlFactory(mapperFactory);
        XyRtcPageListQoFactory pageListQoFactory = new XyRtcPageListQoFactory(poFactory);
        XyRtcVoFactory voFactory = new XyRtcVoFactory(poFactory);
        XyRtcServiceClassFactory serviceClassFactory = new XyRtcServiceClassFactory(poFactory, voFactory, pageListQoFactory);
        XyRtcProviderFactory providerFactory = new XyRtcProviderFactory(poFactory, serviceClassFactory, mapperFactory);

        List<Module> modules = Arrays.asList(
                new Module("PO", "持久化对象", poFactory),
                new Module("MapperClass", "Mybatis Mapper", mapperFactory),
                new Module("MapperXml", "Mybatis Mapper MXL", mapperXmlFactory),
                new Module("Provider", "服务实现", providerFactory),
                new Module("PageListQO", "分页查询对象", pageListQoFactory)
        );

        themeType.setModules(modules);
        return themeType;
    }

}
