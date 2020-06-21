package com.aoeai.spin.accelerator.generate.persistent.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.*;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建持久对象-默认实现
 */
@Service
public class PersistentServiceImpl implements PersistentService {

    @Resource
    private DBService dbService;

    @Resource
    private FreemarkerService freemarkerService;

    @Override
    public PO buildPO(String tableName, IBaseRule baseRule, IPersistentRule persistentRule) {
        Table table = dbService.getTable(tableName);
        PO po = new PO();
        po.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), persistentRule.poPackageSuffix()));
        po.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), persistentRule.poClassNameSuffix()));
        po.setClassComment(table.getComment());
        po.setImportList(buildImportList(table.getColumns()));
        po.setFieldList(buildFieldList(table.getColumns()));

        po.setTemplates(StrUtil.format("{}/dao/bean_po.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                persistentRule.poPath(), po.getClassName());
        po.setFile(new File(fileName));
        po.setTable(table);

        return po;
    }

    @Override
    public void createPOFile(PO po) throws IOException, TemplateException {
        FileTools.buildFile(po.getFile(), freemarkerService.getTemplate(po.getTemplates()), po);
    }

    @Override
    public MapperClass buildMapperClass(String tableName, IBaseRule baseRule, IPersistentRule persistentRule) {
        PO po = buildPO(tableName, baseRule, persistentRule);
        MapperClass mapperClass = new MapperClass();
        mapperClass.setPo(po);

        Table table = dbService.getTable(tableName);
        mapperClass.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), persistentRule.mapperPackageSuffix()));
        mapperClass.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), persistentRule.mapperClassNameSuffix()));
        mapperClass.setClassComment(table.getComment() + " Mapper");
        mapperClass.setTemplates(StrUtil.format("{}/dao/mapper.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                persistentRule.mapperPath(), mapperClass.getClassName());
        mapperClass.setFile(new File(fileName));

        return mapperClass;
    }

    @Override
    public void createMapperClassFile(MapperClass mapperClass) throws IOException, TemplateException {
        FileTools.buildFile(mapperClass.getFile(), freemarkerService.getTemplate(mapperClass.getTemplates()), mapperClass);
    }

    @Override
    public MapperXml buildMapperXml(String tableName, IBaseRule baseRule, IPersistentRule persistentRule) {
        MapperXml xml = new MapperXml();
        MapperClass mapperClass = buildMapperClass(tableName, baseRule, persistentRule);
        xml.setMapperClass(mapperClass);
        String fileName = StrUtil.format("{}{}.xml",
                persistentRule.mapperXmlPath(), mapperClass.getClassName());;
        xml.setTemplates(StrUtil.format("{}/dao/mapper_xml.ftl", baseRule.themes()));
        xml.setFile(new File(fileName));

        return xml;
    }

    @Override
    public void createMapperXmlFile(MapperXml mapperXml) throws IOException, TemplateException {
        FileTools.buildFile(mapperXml.getFile(), freemarkerService.getTemplate(mapperXml.getTemplates()), mapperXml);
    }

    @Override
    public MapperService buildMapperService(String tableName, IBaseRule baseRule, IPersistentRule persistentRule) {
        MapperService mapperService = new MapperService();
        PO po = buildPO(tableName, baseRule, persistentRule);
        mapperService.setPo(po);

        mapperService.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), persistentRule.mapperServicePackageSuffix()));
        Table table = dbService.getTable(tableName);
        mapperService.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), persistentRule.mapperServiceClassSuffix()));
        mapperService.setClassComment(table.getComment() + "服务类");
        mapperService.setTemplates(StrUtil.format("{}/dao/mapper_service.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                persistentRule.mapperServicePath(), mapperService.getClassName());
        mapperService.setFile(new File(fileName));

        return mapperService;
    }

    @Override
    public void createMapperServiceFile(MapperService mapperService) throws IOException, TemplateException {
        FileTools.buildFile(mapperService.getFile(), freemarkerService.getTemplate(mapperService.getTemplates()), mapperService);
    }

    /**
     * 组装import语句列表
     * @param columns
     * @return
     */
    private List<String> buildImportList(List<Column> columns){
        List<String> result = new ArrayList<>();
        for (Column column : columns) {
            String dbType = column.getType();
            String fullName = MySQLType2JavaTypeEnum.javaType(dbType).fullName();
            if (fullName == null) {
                continue;
            }
            result.add(fullName);
        }
        return result;
    }

    /**
     * 组装（数据库对应的）实体类字段列表
     * @param columns
     * @return
     */
    private List<POField> buildFieldList(List<Column> columns){
        List<POField> result = new ArrayList<>();
        for (Column column : columns) {
            POField poField = new POField();
            poField.setName(ClassTools.humpName(column.getName()));
            JavaTypeEnum javaType =  MySQLType2JavaTypeEnum.javaType(column.getType());
            poField.setClassShortName(javaType.shortName());
            poField.setClassFullName(javaType.fullName());
            poField.setComment(column.getComment());

            result.add(poField);
        }
        return result;
    }

}
