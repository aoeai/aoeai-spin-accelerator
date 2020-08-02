package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.themes.constant.ThemeTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板工厂
 * @author aoe
 * @date 2020/8/2
 */
@Component
public class ThemeFactory {

    @Resource
    private POThemesService xyWzmPOThemesService;

    @Resource
    private POThemesService xyChPOThemesService;

    /**
     * 创建PO 主题服务
     * @param theme
     * @return
     */
    public POThemesService buildPOThemesService(String theme) {
        Map<ThemeTypeEnum, POThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA, xyWzmPOThemesService);
        map.put(ThemeTypeEnum.XY_CHANNEL, xyChPOThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

}
