package com.aoeai.spin.accelerator.themes.xy.channel;

import cn.hutool.core.collection.CollectionUtil;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.*;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.themes.POThemesService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author aoe
 * @date 2020/6/7
 */
@Service
public class XyChPOThemesService implements POThemesService {

    @Resource
    private PersistentService persistentService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/channel/config.yml";
    }

    @Override
    public PO getPO(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PO po = persistentService.buildPO(tableName, baseRule, getPersistentRule(baseRule));
        // 用Long替换BigInteger
        Set<String> importList = po.getImportList();
        if (!CollectionUtil.isEmpty(importList)) {
            importList.remove("java.math.BigInteger");
        }
        List<POField> fieldList = po.getFieldList();
        if (!CollectionUtil.isEmpty(fieldList)) {
            for (POField field : fieldList) {
                if ("BigInteger".equals(field.getClassShortName())) {
                    field.setClassShortName(JavaTypeEnum.LONG.shortName());
                    field.setClassFullName(JavaTypeEnum.LONG.fullName());
                }
            }
        }
        po.setPackageName("com.starbuds.server.common.pojo.db");

        String fileName = po.getFile().toString().replaceFirst("api-channel/src/main/java/com/starbuds/server/api/channel/common/pojo/db",
                "common/src/main/java/com/starbuds/server/common/pojo/db");
        po.setFile(new File(fileName));

        return po;
    }

    @Override
    public void createPOFile(String tableName) throws IOException, TemplateException {
        persistentService.createPOFile(getPO(tableName));
    }

    @Override
    public MapperClass getMapperClass(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        MapperClass mapperClass = persistentService.buildMapperClass(tableName, baseRule, getPersistentRule(baseRule));
        mapperClass.setPo(getPO(tableName));
        return mapperClass;
    }

    @Override
    public void createMapperClassFile(String tableName) throws IOException, TemplateException {
        persistentService.createMapperClassFile(getMapperClass(tableName));
    }

    @Override
    public MapperXml getMapperXml(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        MapperXml mapperXml = persistentService.buildMapperXml(tableName, baseRule, getPersistentRule(baseRule));
        mapperXml.setMapperClass(getMapperClass(tableName));
        return mapperXml;
    }

    @Override
    public void createMapperXmlFile(String tableName) throws IOException, TemplateException {
        persistentService.createMapperXmlFile(getMapperXml(tableName));
    }

    @Override
    public MapperService getMapperService(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        return persistentService.buildMapperService(tableName, baseRule, getPersistentRule(baseRule));
    }

    @Override
    public void createMapperServiceFile(String tableName) throws IOException, TemplateException {
//        persistentService.createMapperServiceFile(getMapperService(tableName));
    }

    @Override
    public MapperServiceImpl getMapperServiceImpl(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        return persistentService.buildMapperServiceImpl(tableName, baseRule, getPersistentRule(baseRule));
    }

    @Override
    public void createMapperServiceImplFile(String tableName) throws IOException, TemplateException {
//        persistentService.createMapperServiceImplFile(getMapperServiceImpl(tableName));
    }

    private PersistentRule getPersistentRule(IBaseRule baseRule) {
        return RuleFactory.buildPersistentRule(baseRule);
    }
}
