package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.dao.IDaoRule;
import com.aoeai.spin.accelerator.generate.dao.bean.PO;
import com.aoeai.spin.accelerator.generate.dao.bean.POField;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBTableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建持久对象-默认实现
 */
public class DefaultBuildPOServiceImpl implements BuildPOService {

    private DBTableService dbTableService;
    private IBaseRule baseRule;
    private IDaoRule daoRule;

    public DefaultBuildPOServiceImpl(DBTableService dbTableService, IBaseRule baseRule, IDaoRule daoRule) {
        this.dbTableService = dbTableService;
        this.baseRule = baseRule;
        this.daoRule = daoRule;
    }

    @Override
    public Map<String, PO> allPOMap() {
        Map<String, Table> allTables = dbTableService.allTables();
        Map<String, PO> resultMap = new HashMap<>(allTables.size());
        for (Map.Entry<String, Table> map : allTables.entrySet()) {
            Table table = map.getValue();
            PO po = buildPO(table, baseRule, daoRule);
            resultMap.put(table.getName(), po);
        }

        return resultMap;
    }

    private PO buildPO(Table table, IBaseRule baseRule, IDaoRule daoRule) {
        PO po = new PO();
        po.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), daoRule.poPackageSuffix()));
        po.setClassName(ClassTools.getPoClassName(table.getName(), daoRule.tablePrefixFilter()));
        po.setClassComment(table.getComment());
        po.setImportList(buildImportList(table.getColumns()));
        po.setFieldList(buildFieldList(table.getColumns()));

        return po;
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
