package com.aoeai.spin.accelerator.themes.xy.wazhima;

import com.aoeai.spin.accelerator.themes.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author aoe
 * @date 2020/8/11
 */
@Component
public class XyWzmThemeFactory implements ThemeFactory {

    @Resource
    private POThemesService xyWzmPOThemesService;

    @Resource
    private ServiceThemesService xyWzmServiceThemesService;

    @Resource
    private WebThemesService xyWzmWebThemesService;

    @Resource
    private TestThemesService xyWzmTestThemesService;

    @Override
    public POThemesService buildPOThemesService() {
        return xyWzmPOThemesService;
    }

    @Override
    public ServiceThemesService buildIServiceThemesService() {
        return null;
    }

    @Override
    public ServiceThemesService buildServiceThemesService() {
        return xyWzmServiceThemesService;
    }

    @Override
    public WebThemesService buildIWebThemesService() {
        return null;
    }

    @Override
    public WebThemesService buildWebThemesService() {
        return xyWzmWebThemesService;
    }

    @Override
    public TestThemesService buildTestThemesService() {
        return xyWzmTestThemesService;
    }
}
