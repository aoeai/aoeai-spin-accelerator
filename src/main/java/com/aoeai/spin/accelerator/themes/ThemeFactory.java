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

    @Resource
    private ServiceThemesService xyChIServiceThemesService;

    @Resource
    private ServiceThemesService xyWzmServiceThemesService;

    @Resource
    private WebThemesService xyWzmWebThemesService;

    @Resource
    private TestThemesService xyWzmTestThemesService;

    /**
     * 创建PO主题服务
     * @param theme
     * @return
     */
    public POThemesService buildPOThemesService(String theme) {
        Map<ThemeTypeEnum, POThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA, xyWzmPOThemesService);
        map.put(ThemeTypeEnum.XY_CHANNEL, xyChPOThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

    /**
     * 创建Service接口服务
     * @param theme
     * @return
     */
    public ServiceThemesService buildIServiceThemesService(String theme) {
        Map<ThemeTypeEnum, ServiceThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_CHANNEL, xyChIServiceThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

    public ServiceThemesService buildServiceThemesService(String theme) {
        Map<ThemeTypeEnum, ServiceThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA, xyWzmServiceThemesService);
//        map.put(ThemeTypeEnum.XY_CHANNEL, xyChPOThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

    public WebThemesService buildWebThemesService(String theme) {
        Map<ThemeTypeEnum, WebThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA, xyWzmWebThemesService);
//        map.put(ThemeTypeEnum.XY_CHANNEL, xyChPOThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

    public TestThemesService buildTestThemesService(String theme) {
        Map<ThemeTypeEnum, TestThemesService> map = new HashMap<>();
        map.put(ThemeTypeEnum.XY_WA_ZHI_MA, xyWzmTestThemesService);
//        map.put(ThemeTypeEnum.XY_CHANNEL, xyChPOThemesService);
        return map.get(ThemeTypeEnum.toEnum(theme));
    }

}
