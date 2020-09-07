package com.aoeai.spin.accelerator.themes.frame;

import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 注册模板
 * @author aoe
 * @date 2020/8/24
 */
public abstract class AbstractThemeRegister {

    @Resource
    private ThemeFactory themeFactory;

    @PostConstruct
    private void init(){
        ThemeType themeType = getThemeType();
        themeFactory.registerThemeType(themeType);
    }

    /**
     * 获得模板类型
     * @return
     */
    protected abstract ThemeType getThemeType();

}
