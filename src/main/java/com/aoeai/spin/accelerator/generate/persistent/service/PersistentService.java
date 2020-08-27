package com.aoeai.spin.accelerator.generate.persistent.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.bean.*;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 持久层服务
 */
public interface PersistentService {

    /**
     * 建造（数据库对应的）持久对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    Po buildPO(String tableName, IBaseRule baseRule, PersistentRule persistentRule);

    /**
     * 创建PO文件
     * @param po
     */
    void createPOFile(Po po) throws IOException, TemplateException;

    /**
     * 建造 Mybatis Mapper 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperClass buildMapperClass(String tableName, IBaseRule baseRule, PersistentRule persistentRule);

    /**
     * 创建 Mybatis Mapper 文件
     * @param mapperClass
     */
    void createMapperClassFile(MapperClass mapperClass) throws IOException, TemplateException;

    /**
     * 建造 Mybatis XML 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperXml buildMapperXml(String tableName, IBaseRule baseRule, PersistentRule persistentRule);

    /**
     * 创建 Mybatis XML 文件
     * @param mapperXml
     */
    void createMapperXmlFile(MapperXml mapperXml) throws IOException, TemplateException;

    /**
     * 建造 Mybatis Mapper Service 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperService buildMapperService(String tableName, IBaseRule baseRule, PersistentRule persistentRule);

    /**
     * 创建 Mybatis Mapper Service 文件
     * @param mapperService
     */
    void createMapperServiceFile(MapperService mapperService) throws IOException, TemplateException;

    /**
     * 建造 Mybatis Mapper ServiceImpl 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperServiceImpl buildMapperServiceImpl(String tableName, IBaseRule baseRule, PersistentRule persistentRule);

    /**
     * 创建 Mybatis Mapper ServiceImpl 文件
     * @param mapperServiceImpl
     */
    void createMapperServiceImplFile(MapperServiceImpl mapperServiceImpl) throws IOException, TemplateException;
}
