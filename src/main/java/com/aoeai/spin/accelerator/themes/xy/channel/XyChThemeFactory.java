package com.aoeai.spin.accelerator.themes.xy.channel;

import com.aoeai.spin.accelerator.themes.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author aoe
 * @date 2020/8/11
 */
@Component
public class XyChThemeFactory implements ThemeFactory {

    @Resource
    private POThemesService xyChPOThemesService;

    @Resource
    private ServiceThemesService xyChIServiceThemesService;

    @Resource
    private ServiceThemesService xyChServiceThemesService;

    @Resource
    private WebThemesService xyChWebThemesService;

    @Resource
    private WebThemesService xyChIWebThemesService;

    @Resource
    private TestThemesService xyChTestThemesService;

    @Override
    public POThemesService buildPOThemesService() {
        return xyChPOThemesService;
    }

    @Override
    public ServiceThemesService buildIServiceThemesService() {
        return xyChIServiceThemesService;
    }

    @Override
    public ServiceThemesService buildServiceThemesService() {
        return xyChServiceThemesService;
    }

    @Override
    public WebThemesService buildIWebThemesService() {
        return xyChIWebThemesService;
    }

    @Override
    public WebThemesService buildWebThemesService() {
        return xyChWebThemesService;
    }

    @Override
    public TestThemesService buildTestThemesService() {
        return xyChTestThemesService;
    }
}
