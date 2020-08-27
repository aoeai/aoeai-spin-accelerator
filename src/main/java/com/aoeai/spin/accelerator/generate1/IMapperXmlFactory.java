package com.aoeai.spin.accelerator.generate1;

import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;

/**
 * Mybatis Mapper XML 文件工厂
 * @author aoe
 */
public interface IMapperXmlFactory extends BuildFactory {

    /**
     * 创建 Mybatis Mapper XML 文件
     * @param tableName
     * @return
     */
    @Override
    MapperXml build(String tableName);
}
