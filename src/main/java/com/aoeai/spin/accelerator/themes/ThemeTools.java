package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.themes.constant.ThemeTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板工具类
 * @author aoe
 * @date 2020/8/11
 */
@Component
public class ThemeTools {

    @Resource
    private ThemeFactory xyWzmThemeFactory;

    @Resource
    private ThemeFactory xyChThemeFactory;

    private Map<String, ThemeFactory> map;

    @PostConstruct
    private void init(){
        map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA.getTheme(), xyWzmThemeFactory);
        map.put(ThemeTypeEnum.XY_CHANNEL.getTheme(), xyChThemeFactory);
    }

    /**
     * 返回模板工厂
     * @param theme 模板名称
     * @return 模板工厂
     */
    public ThemeFactory themeFactory(String theme) {
        return map.get(theme);
    }
}
