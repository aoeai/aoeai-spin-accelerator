package com.aoeai.spin.accelerator.themes.customize.xy.dsuser.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsForm;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsFormField;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsUserFormCreateFactory extends AbstractJavaFileFactory<XyDsForm> {

    private IPoFactory poFactory;

    public XyDsUserFormCreateFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyDsForm build(String tableName) {
        return create(tableName, new XyDsForm());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dsuser/config/form-create.yaml";
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
            if (column.isNullable()) {
                continue;
            }
            List<String> checkTagList = new ArrayList<>();
            JavaTypeEnum javaType = MySQLType2JavaTypeEnum.javaType(column.getType(), column.getDbMaxLength());
            String comment = column.getComment();
            if (JavaTypeEnum.STRING == javaType) {
                checkTagList.add(StrUtil.format("@NotBlank(message = \"{}不能为空\")", comment));
            }else {
                checkTagList.add(StrUtil.format("@NotNull(message = \"{}不能为空\")", comment));
            }
            checkTagListMap.put(column.getHumpName(), checkTagList);
        }

        List<XyDsFormField> formFieldList = new ArrayList<>(po.getTable().getColumns().size());
        for (POField poField : po.getFieldList()) {
            XyDsFormField formField = new XyDsFormField();
            BeanUtils.copyProperties(poField, formField);
            formField.setCheckTagList(checkTagListMap.get(formField.getName()));
            formFieldList.add(formField);
        }
        builder.setFieldList(formFieldList);
        builder.setImportList(po.getImportList());
    }
}
