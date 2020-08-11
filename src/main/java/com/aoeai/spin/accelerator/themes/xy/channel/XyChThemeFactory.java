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
        return null;
    }

    @Override
    public WebThemesService buildWebThemesService() {
        return null;
    }

    @Override
    public TestThemesService buildTestThemesService() {
        return null;
    }
}
