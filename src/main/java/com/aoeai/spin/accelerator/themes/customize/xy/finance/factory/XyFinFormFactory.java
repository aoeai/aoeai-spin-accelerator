package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinForm;
import com.aoeai.spin.accelerator.themes.customize.xy.finance.bean.XyFinFormField;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * 表单类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyFinFormFactory extends AbstractJavaFileFactory<XyFinForm> {

    private IPoFactory poFactory;

    public XyFinFormFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyFinForm build(String tableName) {
        return create(tableName, new XyFinForm());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/finance/config/form.yaml";
    }

    @Override
    protected IPoFactory poFactory() {
        return poFactory;
    }

    /**
     * 手动创建逻辑
     *
     * @param tableName
     */
    @Override
    protected void manualCreate(String tableName) {
        Po po = poFactory.build(tableName);
        // 添加效验标签
        Map<String, List<String>> checkTagListMap = new HashMap<>();
        for (Column column : po.getTable().getColumns()) {
            if (column.getIsPrimaryKey()) {
                checkTagListMap.put(column.getName(), Collections.EMPTY_LIST);
                continue;
            }

            List<String> checkTagList = new ArrayList<>();
            JavaTypeEnum javaType = MySQLType2JavaTypeEnum.javaType(column.getType(), column.getDbMaxLength());
            String comment = column.getComment();
            if (JavaTypeEnum.STRING == javaType) {
                checkTagList.add(StrUtil.format("@XyNotBlank(msg = \"{}不能为空\")", comment));
            }else {
                checkTagList.add(StrUtil.format("@XyNotNull(msg = \"{}不能为空\")", comment));
            }
            checkTagListMap.put(column.getName(), checkTagList);
        }

        List<XyFinFormField> formFieldList = new ArrayList<>(po.getTable().getColumns().size());
        for (POField poField : po.getFieldList()) {
            XyFinFormField formField = new XyFinFormField();
            BeanUtils.copyProperties(poField, formField);
            formField.setCheckTagList(checkTagListMap.get(formField.getName()));
            formFieldList.add(formField);
        }
        builder.setFieldList(formFieldList);
        builder.setImportList(po.getImportList());
    }
}
