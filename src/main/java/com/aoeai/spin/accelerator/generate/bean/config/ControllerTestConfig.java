package com.aoeai.spin.accelerator.generate.bean.config;

import lombok.Data;

/**
 * 控制器测试类 配置信息
 * @author aoe
 * @date 2020/8/24
 */
@Data
public class ControllerTestConfig extends JavaConfig {

    /**
     * 测试主机地址
     */
    private String hostTest = "";
}
