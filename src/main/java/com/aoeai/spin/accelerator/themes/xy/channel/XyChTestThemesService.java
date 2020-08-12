package com.aoeai.spin.accelerator.themes.xy.channel;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.test.bean.ControllerTest;
import com.aoeai.spin.accelerator.generate.test.rule.TestRule;
import com.aoeai.spin.accelerator.generate.test.service.TestService;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.themes.TestThemesService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/23
 */
@Service
public class XyChTestThemesService implements TestThemesService {

    @Resource
    private TestService testService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/channel/config.yml";
    }

    @Override
    public ControllerTest getControllerTest(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        TestRule testRule = RuleFactory.buildTestRule(baseRule);
        ControllerTest test = testService.buildControllerTest(tableName, baseRule, persistentRule, webRule, testRule);

        // 个性化
        test.setPackageName("com.starbuds.apichannel");
        test.setUrlCommonPath("api-channel/v1/channel/补全路径/");
        return test;
    }

    @Override
    public void createControllerTestFile(String tableName) throws IOException, TemplateException {
        testService.createControllerTestFile(getControllerTest(tableName));
    }
}
