package com.aoeai.spin.accelerator.generate;

import com.aoeai.spin.accelerator.generate.common.IGenerateProperty;

/**
 * 建造工厂
 * @author aoe
 * @date 2020/8/25
 */
public interface BuildFactory {

    /**
     * 建造模块对象
     * @param tableName
     * @param <T>
     * @return
     */
    <T extends IGenerateProperty>T build(String tableName);
}
