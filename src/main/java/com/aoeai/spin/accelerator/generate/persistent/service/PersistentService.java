package com.aoeai.spin.accelerator.generate.persistent.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperService;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
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
    PO buildPO(String tableName, IBaseRule baseRule, IPersistentRule persistentRule);

    /**
     * 创建PO文件
     * @param po
     */
    void createPOFile(PO po) throws IOException, TemplateException;

    /**
     * 建造 Mybatis Mapper 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperClass buildMapperClass(String tableName, IBaseRule baseRule, IPersistentRule persistentRule);

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
    MapperXml buildMapperXml(String tableName, IBaseRule baseRule, IPersistentRule persistentRule);

    /**
     * 创建 Mybatis XML 文件
     * @param mapperXml
     */
    void createMapperXmlFile(MapperXml mapperXml) throws IOException, TemplateException;

    /**
     * 建造 Mybatis Mapper 文件对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    MapperService buildMapperService(String tableName, IBaseRule baseRule, IPersistentRule persistentRule);

    /**
     * 创建 Mybatis Mapper 文件
     * @param mapperService
     */
    void createMapperServiceFile(MapperService mapperService) throws IOException, TemplateException;
}
