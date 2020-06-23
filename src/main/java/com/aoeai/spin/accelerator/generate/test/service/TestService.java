package com.aoeai.spin.accelerator.generate.test.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.test.bean.ControllerTest;
import com.aoeai.spin.accelerator.generate.test.rule.TestRule;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/23
 */
public interface TestService {

    /**
     * 建造测试控制器类
     * @param tableName
     * @param baseRule
     * @return
     */
    ControllerTest buildControllerTest(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                                       WebRule webRule, TestRule testRule);

    /**
     * 创建测试控制器文件
     * @param controllerTest
     */
    void createControllerTestFile(ControllerTest controllerTest) throws IOException, TemplateException;
}
