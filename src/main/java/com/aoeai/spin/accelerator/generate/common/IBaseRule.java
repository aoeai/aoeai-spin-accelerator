package com.aoeai.spin.accelerator.generate.common;

/**
 * （生成时的）基础规则
 */
public interface IBaseRule {

    /**
     * @return 工程根路径的包名
     */
    String rootPackageName();

    /**
     * @return 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
     */
    String generatorRootPath();
}
