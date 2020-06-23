package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Service 主题服务
 */
public interface ServiceThemesService {

    ServiceClass getServiceClass(String tableName);

    /**
     * 创建ServiceClass文件
     * @param tableName
     */
    void createServiceClassFile(String tableName) throws IOException, TemplateException;
}
