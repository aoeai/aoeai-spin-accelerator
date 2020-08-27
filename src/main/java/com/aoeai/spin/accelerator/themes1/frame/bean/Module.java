package com.aoeai.spin.accelerator.themes1.frame.bean;

import com.aoeai.spin.accelerator.generate1.BuildFactory;
import lombok.Getter;

/**
 * 功能模块
 * @author aoe
 * @date 2020/8/25
 */
@Getter
public class Module {

    /**
     * 模块代码
     */
    private String code;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 对象建造工厂
     */
    private BuildFactory factory;

    public Module(String code, String name, BuildFactory factory) {
        this.code = code;
        this.name = name;
        this.factory = factory;
    }
}
