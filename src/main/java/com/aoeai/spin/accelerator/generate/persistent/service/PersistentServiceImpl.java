package com.aoeai.spin.accelerator.generate.persistent.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
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
        po.setClassName(ClassTools.getPoClassName(table.getName(), persistentRule.tablePrefixFilter(), persistentRule.poClassNameSuffix()));
        po.setClassComment(table.getComment());
        po.setImportList(buildImportList(table.getColumns()));
        po.setFieldList(buildFieldList(table.getColumns()));

        po.setTemplates(StrUtil.format("{}/bean_po.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                baseRule.generatorRootPath(), po.getClassName());
        po.setFile(new File(fileName));
        po.setTable(table);

        return po;
    }

    @Override
    public void createPOFile(PO po) throws IOException, TemplateException {
        FileTools.buildFile(po.getFile(), freemarkerService.getTemplate(po.getTemplates()), po);
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
