package com.aoeai.spin.accelerator.themes.customize.qm.kuyin.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.bean.config.PoConfig;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.XyWzmTools;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.aoeai.spin.accelerator.generate.utils.ClassTools.buildImportList;

/**
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class QmKunyinSaveRpcParamFactory implements IPoFactory {

    @Resource
    private DBService dbService;

    /**
     * 创建 SaveRpcParam
     *
     * @param tableName
     * @return
     */
    @Override
    public Po build(String tableName) {
        Po po = new Po();
        PoConfig cfg = ConfigTools.getConfig("/themes/qm/kuyin/config/save_rpc_param.yaml", PoConfig.class);
        BeanUtils.copyProperties(cfg, po);

        Table table = dbService.getTable(tableName);
        po.setClassName(ClassTools.buildClassName(table.getName(), cfg.getTablePrefixFilter(), cfg.getSuffix()));
        po.setClassNameWithoutSuffix(po.getClassName().substring(0, po.getClassName().length() - cfg.getSuffix().length()));
        po.setClassComment(table.getComment());
        po.setImportList(buildImportList(table.getColumns()));
        po.setFieldList(buildFieldList(table.getColumns()));
        po.setPackageName(XyWzmTools.replaceAllModelName(po.getPackageName(), tableName));

        String fileName = StrUtil.format("{}{}.java",
                XyWzmTools.replaceAllModelName(cfg.getFilePath(), tableName), po.getClassName());
        po.setFile(new File(fileName));
        po.setTable(table);

        return po;
    }

    /**
     * 组装（数据库对应的）实体类字段列表
     * @param columns
     * @return
     */
    private List<POField> buildFieldList(List<Column> columns){
        List<POField> result = new ArrayList<>();
        for (Column column : columns) {
            if (ignoreColumn(column.getName())) {
                continue;
            }

            POField poField = new POField();
            poField.setName(ClassTools.humpName(column.getName()));
            JavaTypeEnum javaType =  MySQLType2JavaTypeEnum.javaType(column.getType(), column.getLength());
            poField.setClassShortName(javaType.shortName());
            poField.setClassFullName(javaType.fullName());
            poField.setComment(column.getComment());
            poField.setIsPrimaryKey(column.getIsPrimaryKey());

            result.add(poField);
        }
        return result;
    }

    private static boolean ignoreColumn(String columnName){
        return List.of("id", "creator", "modifier", "create_time", "update_time", "is_deleted").contains(columnName);
    }
}
