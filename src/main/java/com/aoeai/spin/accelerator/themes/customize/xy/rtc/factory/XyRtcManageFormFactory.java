package com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcForm;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.bean.XyRtcFormField;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * Manage 模块表单类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyRtcManageFormFactory extends AbstractJavaFileFactory<XyRtcForm> {

    private IPoFactory poFactory;

    public XyRtcManageFormFactory(IPoFactory poFactory) {
        this.poFactory = poFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyRtcForm build(String tableName) {
        var clazz = create(tableName, new XyRtcForm());
        clazz.setTemplates("xy/rtc/form.ftl");
        return clazz;
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/rtc/config/manage-form.yaml";
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
        var po = poFactory.build(tableName);
        // 添加效验标签
        Map<String, List<String>> checkTagListMap = new HashMap<>();
        for (Column column : po.getTable().getColumns()) {
            if (Boolean.TRUE.equals(column.getIsPrimaryKey())) {
                checkTagListMap.put(column.getName(), Collections.emptyList());
                continue;
            }

            List<String> checkTagList = new ArrayList<>();
            var javaType = MySQLType2JavaTypeEnum.javaType(column.getType(), column.getDbMaxLength());
            String comment = column.getComment();
            if (JavaTypeEnum.STRING == javaType) {
                checkTagList.add(StrUtil.format("@XyNotBlank(msg = \"{}不能为空\")", comment));
            }else {
                checkTagList.add(StrUtil.format("@XyNotNull(msg = \"{}不能为空\")", comment));
            }
            checkTagListMap.put(column.getName(), checkTagList);
        }

        List<XyRtcFormField> formFieldList = new ArrayList<>(po.getTable().getColumns().size());
        for (POField poField : po.getFieldList()) {
            var formField = new XyRtcFormField();
            BeanUtils.copyProperties(poField, formField);
            formField.setCheckTagList(checkTagListMap.get(formField.getName()));
            formFieldList.add(formField);
        }
        builder.setFieldList(formFieldList);
        builder.setImportList(po.getImportList());
    }
}
