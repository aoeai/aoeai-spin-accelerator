package com.aoeai.spin.accelerator.generate.service.rule;

/**
 * （生成时的）Service规则
 */
public interface ServiceRule {

    /**
     * @return Service 类存储位置
     */
    String servicePath();

    /**
     * @return Service 类所在位置的包名后缀
     */
    String servicePackageSuffix();

    /**
     *
     * @return Service 类后缀
     */
    String serviceSuffix();
}
