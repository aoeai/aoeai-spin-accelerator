package com.aoeai.spin.accelerator.generate.test.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.test.bean.ControllerTest;
import com.aoeai.spin.accelerator.generate.test.rule.TestRule;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.generate.web.bean.FormField;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.generate.web.service.WebService;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aoe
 * @date 2020/6/23
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private DBService dbService;

    @Resource
    private FreemarkerService freemarkerService;

    @Resource
    private WebService webService;

    @Override
    public ControllerTest buildControllerTest(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                                              WebRule webRule, TestRule testRule) {
        ControllerTest controllerTest = new ControllerTest();
        Table table = dbService.getTable(tableName);
        controllerTest.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), testRule.controllerTestPackageSuffix()));
        controllerTest.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), testRule.controllerTestSuffix()));
        controllerTest.setClassComment(table.getComment() + "控制器测试");

        controllerTest.setHostTest(testRule.hostTest());
        String urlPrefix = ClassTools.getModelName(table.getName(), persistentRule.tablePrefixFilter());
        controllerTest.setUrlPrefix(urlPrefix);

        controllerTest.setForm(webService.buildForm(tableName, baseRule, persistentRule, webRule));
        controllerTest.setPageListQO(webService.buildPageListQO(tableName, baseRule, persistentRule, webRule));

        // 新增方法参数
        List<String> createParamList = new ArrayList<>();
        // 新增/更新方法参数
        List<String> createOrUpdateParamList = new ArrayList<>();
        // 更新方法参数
        List<String> updateParamList = new ArrayList<>();
        for (FormField field : controllerTest.getForm().getFieldList()) {
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

            String createOrUpdateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), createOrUpdateValue);
            createOrUpdateParamList.add(createOrUpdateParam);

            String updateParam = StrUtil.format("params.put(\"{}\", \"{}\");", field.getName(), updateValue);
            updateParamList.add(updateParam);
        }
        controllerTest.setCreateParamList(createParamList);
        controllerTest.setCreateOrUpdateParamList(createOrUpdateParamList);
        controllerTest.setUpdateParamList(updateParamList);

        controllerTest.setTemplates(StrUtil.format("{}/test/controller_test.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                testRule.controllerTestPath(), controllerTest.getClassName());
        controllerTest.setFile(new File(fileName));


        return controllerTest;
    }

    @Override
    public void createControllerTestFile(ControllerTest controllerTest) throws IOException, TemplateException {
        FileTools.buildFile(controllerTest.getFile(), freemarkerService.getTemplate(controllerTest.getTemplates()), controllerTest);
    }
}
