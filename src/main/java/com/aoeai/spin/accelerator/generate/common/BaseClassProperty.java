package com.aoeai.spin.accelerator.generate.common;

import lombok.Data;

import java.io.File;
import java.util.Set;

/**
 * 类的基本属性
 * @author aoe
 * @date 2020/6/21
 */
@Data
public class BaseClassProperty implements ITemplates {

    /**
     * 包名
     */
    private String packageName;

    /**
     * import语句
     */
    private Set<String> importList;

    /**
     * 类名
     */
    private String className;

    /**
     * 类注释
     */
    private String classComment;

    /**
     * Java文件
     */
    private File file;

    /**
     * 模板文件路径
     */
    private String templates;
}
