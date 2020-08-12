package com.aoeai.spin.accelerator.themes;

/**
 * 模板工厂
 */
public interface ThemeFactory {

    /**
     * 创建PO主题服务
     * @return
     */
    POThemesService buildPOThemesService();

    /**
     * 创建Service主题服务（接口）
     * @return
     */
    ServiceThemesService buildIServiceThemesService();

    /**
     * 创建Service主题服务（实现类）
     * @return
     */
    ServiceThemesService buildServiceThemesService();

    /**
     * 创建Web层主题服务（接口）
     * @return
     */
    WebThemesService buildIWebThemesService();

    /**
     * 创建Web层主题服务（实现类）
     * @return
     */
    WebThemesService buildWebThemesService();

    /**
     * 创建单元测试主题服务
     * @return
     */
    TestThemesService buildTestThemesService();
}
