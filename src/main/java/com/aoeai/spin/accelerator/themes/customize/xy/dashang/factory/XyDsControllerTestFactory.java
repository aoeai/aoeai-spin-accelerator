package com.aoeai.spin.accelerator.themes.customize.xy.dashang.factory;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.generate.bean.config.ControllerTestConfig;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsControllerTest;
import com.aoeai.spin.accelerator.themes.customize.xy.dashang.bean.XyDsFormField;
import com.aoeai.spin.accelerator.themes.customize.xy.wazhima.XyWzmTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aoe
 * @date 2020/8/26
 */
public class XyDsControllerTestFactory extends AbstractJavaFileFactory<XyDsControllerTest> {

    private IPoFactory poFactory;
    private XyDsPageListQoFactory pageListQoFactory;
    private XyDsFormCreateFactory formFactory;

    public XyDsControllerTestFactory(IPoFactory poFactory, XyDsPageListQoFactory pageListQoFactory,
                                     XyDsFormCreateFactory formFactory) {
        this.poFactory = poFactory;
        this.pageListQoFactory = pageListQoFactory;
        this.formFactory = formFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyDsControllerTest build(String tableName) {
        return create(tableName, new XyDsControllerTest());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/dashang/config/controller-test.yaml";
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
        builder.setClassComment(po.getClassComment() + "控制器测试");
        builder.setPageListQO(pageListQoFactory.build(tableName));
        builder.setForm(formFactory.build(tableName));

        String urlPrefix = ClassTools.getModelName(po.getTable().getName(), po.getTablePrefixFilter());
        builder.setUrlPrefix(urlPrefix);

        // 新增方法参数
        List<String> createParamList = new ArrayList<>();
        // 新增/更新方法参数
        List<String> createOrUpdateParamList = new ArrayList<>();
        // 更新方法参数
        List<String> updateParamList = new ArrayList<>();

        JSONObject json = new JSONObject();
        for (XyDsFormField field : builder.getForm().getFieldList()) {
            // 不填充主键
            if ("tid".equals(field.getName())) {
                continue;
            }
            String value = "0";
            String createOrUpdateValue = "3";
            String updateValue = "5";
            String classShortName = field.getClassShortName();
            if (JavaTypeEnum.STRING.shortName().equals(classShortName)) {
                value = field.getComment();
                createOrUpdateValue += createOrUpdateValue;
                updateValue += createOrUpdateValue;
            } else if (JavaTypeEnum.BIG_DECIMAL.shortName().equals(classShortName)
                    || JavaTypeEnum.DOUBLE.shortName().equals(classShortName)
                    || JavaTypeEnum.FLOAT.shortName().equals(classShortName)) {
                value = "0.0";
                createOrUpdateValue = "3.0";
                updateValue = "5.0";
            }
            String param = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), value);
            createParamList.add(param);

            json.put(field.getName(), value);

            String createOrUpdateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), createOrUpdateValue);
            createOrUpdateParamList.add(createOrUpdateParam);

            String updateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), updateValue);
            updateParamList.add(updateParam);
        }
        builder.setCreateParamList(createParamList);
        builder.setCreateOrUpdateParamList(createOrUpdateParamList);
        builder.setUpdateParamList(updateParamList);

        System.out.println(JSON.toJSONString(json, true));
    }

    @Override
    protected void doEnd(String tableName) {
        builder = XyWzmTools.replaceModelName(builder, tableName);
    }

    @Override
    protected Class getConfigType() {
        return ControllerTestConfig.class;
    }
}
