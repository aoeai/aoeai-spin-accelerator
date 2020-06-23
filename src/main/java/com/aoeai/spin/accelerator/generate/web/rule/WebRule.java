package com.aoeai.spin.accelerator.generate.web.rule;

/**
 * （生成时的）Web 层规则
 */
public interface WebRule {

    /**
     * @return  分页查询类存储位置
     */
    String pageListQOPath();

    /**
     * @return 页面查询类所在位置的包名后缀
     */
    String pageListQOPackageSuffix();

    /**
     *
     * @return 页面查询类后缀
     */
    String pageListQOSuffix();

    /**
     * @return  VO类存储位置
     */
    String voPath();

    /**
     * @return VO类所在位置的包名后缀
     */
    String voPackageSuffix();

    /**
     *
     * @return VO类后缀
     */
    String voSuffix();

    /**
     * @return  Form 表单类存储位置
     */
    String formPath();

    /**
     * @return Form 表单类所在位置的包名后缀
     */
    String formPackageSuffix();

    /**
     *
     * @return Form 表单类后缀
     */
    String formSuffix();

    /**
     * @return  控制器类存储位置
     */
    String controllerPath();

    /**
     * @return 控制器类所在位置的包名后缀
     */
    String controllerPackageSuffix();

    /**
     *
     * @return 控制器类后缀
     */
    String controllerSuffix();
}
