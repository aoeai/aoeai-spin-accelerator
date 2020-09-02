package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate1.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.XyWzmTools;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean.XyWzmForm;
import com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean.XyWzmFormField;
import org.springframework.beans.BeanUtils;

import java.util.*;

import static com.aoeai.spin.accelerator.generate.utils.ClassTools.buildImportList;

/**
 * @author aoe
 * @date 2020/8/26
 */
public class XyWzmFormFactory extends AbstractJavaFileFactory<XyWzmForm> {

    private IPoFactory poFactory;

    public XyWzmFormFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyWzmForm build(String tableName) {
        return create(tableName, new XyWzmForm());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/wazhima1/config/form.yaml";
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
        Set<String> importList =  buildImportList(po.getTable().getColumns());
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
                checkTagList.add(StrUtil.format("@NotBlank(message = \"{}不能为空\")", comment));
                importList.add("javax.validation.constraints.NotBlank");

                checkTagList.add(StrUtil.format("@Length(max = {}, message = \"{}最大长度{}个字符\")",
                        column.getDbMaxLength(), comment, column.getDbMaxLength()));
                importList.add("org.hibernate.validator.constraints.Length");
            }else {
                checkTagList.add(StrUtil.format("@NotNull(message = \"{}不能为空\")", comment));
                importList.add("javax.validation.constraints.NotNull");
            }

            checkTagListMap.put(column.getName(), checkTagList);
        }
        builder.setImportList(importList);

        List<XyWzmFormField> formFieldList = new ArrayList<>(po.getTable().getColumns().size());
        for (POField poField : po.getFieldList()) {
            XyWzmFormField formField = new XyWzmFormField();
            BeanUtils.copyProperties(poField, formField);
            formField.setCheckTagList(checkTagListMap.get(formField.getName()));
            formFieldList.add(formField);
        }
        builder.setFieldList(formFieldList);
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }
}
