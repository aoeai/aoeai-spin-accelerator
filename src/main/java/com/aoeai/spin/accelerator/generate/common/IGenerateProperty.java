package com.aoeai.spin.accelerator.generate.common;

import java.io.File;

/**
 * 生成对象共同属性
 * @author aoe
 * @date 2020/8/2
 */
public interface IGenerateProperty {

    /**
     * 模板文件路径
     * @return 模板文件路径
     */
    String getTemplates();

    /**
     * 生成的文件
     * @return
     */
    File getFile();
}
