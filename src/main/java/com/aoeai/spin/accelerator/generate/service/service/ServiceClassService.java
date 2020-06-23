package com.aoeai.spin.accelerator.generate.service.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 服务类
 * @author aoe
 * @date 2020/6/22
 */
public interface ServiceClassService {

    /**
     * 建造服务类
     * @param tableName
     * @param baseRule
     * @param serviceRule
     * @return
     */
    ServiceClass buildServiceClass(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                                   ServiceRule serviceRule, WebRule webRule);

    /**
     * 创建服务类文件
     * @param serviceClass
     */
    void createServiceClassFile(ServiceClass serviceClass) throws IOException, TemplateException;
}
