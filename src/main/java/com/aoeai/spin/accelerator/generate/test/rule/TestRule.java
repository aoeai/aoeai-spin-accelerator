package com.aoeai.spin.accelerator.generate.test.rule;

/**
 * 单元测试规则
 * @author aoe
 * @date 2020/6/23
 */
public interface TestRule {

    /**
     * @return 控制器测试类存储位置
     */
    String controllerTestPath();

    /**
     * @return 控制器测试类所在位置的包名后缀
     */
    String controllerTestPackageSuffix();

    /**
     *
     * @return 控制器测试类后缀
     */
    String controllerTestSuffix();

    /**
     *
     * @return 测试主机地址
     */
    String hostTest();
}
