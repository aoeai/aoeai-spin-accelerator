package com.aoeai.spin.accelerator.generate1;

import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;

/**
 * Mybatis Mapper 文件工厂
 * @author aoe
 */
public interface IMapperFactory extends BuildFactory {

    /**
     * 创建 Mybatis Mapper 文件
     * @param tableName
     * @return
     */
    @Override
    MapperClass build(String tableName);
}
