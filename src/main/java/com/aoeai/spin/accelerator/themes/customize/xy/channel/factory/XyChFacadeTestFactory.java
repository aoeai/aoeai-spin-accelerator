package com.aoeai.spin.accelerator.themes.customize.xy.channel.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.AbstractJavaFileFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChFacadeTest;
import com.aoeai.spin.accelerator.themes.customize.xy.channel.bean.XyChFormField;

import java.util.ArrayList;
import java.util.List;

/**
 * 门面控制器测试类工厂
 * @author aoe
 * @date 2020/8/26
 */
public class XyChFacadeTestFactory extends AbstractJavaFileFactory<XyChFacadeTest> {

    private IPoFactory poFactory;
    private XyChFormFactory formFactory;
    private XyChPageListQoFactory pageListQoFactory;

    public XyChFacadeTestFactory(IPoFactory poFactory, XyChFormFactory formFactory, XyChPageListQoFactory pageListQoFactory) {
        this.poFactory = poFactory;
        this.formFactory = formFactory;
        this.pageListQoFactory = pageListQoFactory;
    }

    /**
     * 建造模块对象
     *
     * @param tableName
     * @return
     */
    @Override
    public XyChFacadeTest build(String tableName) {
        return create(tableName, new XyChFacadeTest());
    }

    /**
     * 存放配置信息的yaml文件
     *
     * @return
     */
    @Override
    protected String configYaml() {
        return "/themes/xy/channel/config/facade-test.yaml";
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
        builder.setForm(formFactory.build(tableName));
        builder.setPageListQO(pageListQoFactory.build(tableName));

        // 新增方法参数
        List<String> createParamList = new ArrayList<>();
        // 新增/更新方法参数
        List<String> createOrUpdateParamList = new ArrayList<>();
        // 更新方法参数
        List<String> updateParamList = new ArrayList<>();
        for (XyChFormField field : builder.getForm().getFieldList()) {
            // 不填充主键
            if ("tid".equals(field.getName())) {
                continue;
            }
            var value = "0";
            var createOrUpdateValue = "3";
            var updateValue = "5";
            var classShortName = field.getClassShortName();
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

            String createOrUpdateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), createOrUpdateValue);
            createOrUpdateParamList.add(createOrUpdateParam);

            String updateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), updateValue);
            updateParamList.add(updateParam);
        }
        builder.setCreateParamList(createParamList);
        builder.setCreateOrUpdateParamList(createOrUpdateParamList);
        builder.setUpdateParamList(updateParamList);
        builder.setUrlCommonPath("api-channel/v1/channel/补全路径/");
        builder.setClassComment(builder.getClassComment() + "测试");
    }
}
