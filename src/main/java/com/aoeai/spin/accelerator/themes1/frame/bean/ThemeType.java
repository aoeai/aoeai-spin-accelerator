package com.aoeai.spin.accelerator.themes1.frame.bean;

import lombok.Data;

import java.util.List;

/**
 * 模板类型
 * @author aoe
 * @date 2020/8/24
 */
@Data
public class ThemeType {

    /**
     * 模板代码
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模块列表
     */
    private List<Module> modules;
}
