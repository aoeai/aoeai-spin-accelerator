package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.generate.test.bean.ControllerTest;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 单元测试 主题服务
 */
public interface TestThemesService {

    ControllerTest getControllerTest(String tableName);

    /**
     * 创建ControllerTest文件
     * @param tableName
     */
    void createControllerTestFile(String tableName) throws IOException, TemplateException;
}
