package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.dao.IDaoRule;
import com.aoeai.spin.accelerator.generate.dao.bean.PO;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfiguration;
import com.aoeai.spin.accelerator.refining.db.service.ITableTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建持久对象-默认实现
 */
public class DefaultBuildPO implements IBuildPO {

    private ITableTools tableTools;

    public DefaultBuildPO(ITableTools tableTools) {
        this.tableTools = tableTools;
    }

    @Override
    public Map<String, PO> allPOMap(IDbConfiguration dbConf, IBaseRule baseRule, IDaoRule daoRule) {
        Map<String, Table> allTables = tableTools.allTables(dbConf);
        for (Map.Entry<String, Table> map : allTables.entrySet()) {
            Table table = map.getValue();
            buildPO(table, baseRule, daoRule);
        }

        return null;
    }

    private PO buildPO(Table table, IBaseRule baseRule, IDaoRule daoRule) {
        String packageName = ClassTools.buildPackageName(baseRule.rootPackageName(),
                daoRule.poPackageSuffix());
        PO po = new PO();
        return null;
    }

    private List<String> buildImportList(List<Column> columns){
        return null;
    }

    /**
     * Java类名信息
     */
    private class JavaClassName{
        /**
         * 类名简称（无包名）
         */
        private String shortName;

        /**
         * 类名全称（有包名）
         */
        private String fullName;

        public JavaClassName(String shortName, String fullName) {
            this.shortName = shortName;
            this.fullName = fullName;
        }

        public String getShortName() {
            return shortName;
        }

        public String getFullName() {
            return fullName;
        }
    }

    private void initByMySQL() {
        JavaClassName STRING = new JavaClassName("String", "");
        JavaClassName INTEGER = new JavaClassName("Integer", "");
        JavaClassName BIG_INTEGER = new JavaClassName("BigInteger", "java.math.BigInteger");
        JavaClassName FLOAT = new JavaClassName("Float", "");
        JavaClassName BIG_DECIMAL = new JavaClassName("BigDecimal", "java.math.BigDecimal");
        JavaClassName DATE = new JavaClassName("Date", "java.util.Date");

        Map<String, JavaClassName> nameMap = new HashMap<>();
        nameMap = new HashMap();
        nameMap.put("varchar", STRING);
        nameMap.put("char", STRING);
        nameMap.put("nvarchar", STRING);
        nameMap.put("text", STRING);
        nameMap.put("longtext", STRING);

        nameMap.put("tinyint", INTEGER);
        nameMap.put("smallint", INTEGER);
        nameMap.put("int", INTEGER);
        nameMap.put("bigint", BIG_INTEGER);

        nameMap.put("float", FLOAT);
        nameMap.put("decimal", BIG_DECIMAL);

        nameMap.put("date", DATE);
        nameMap.put("datetime", DATE);
        nameMap.put("timestamp", DATE);
    }
}
