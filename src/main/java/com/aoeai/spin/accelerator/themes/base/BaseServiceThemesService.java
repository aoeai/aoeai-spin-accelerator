package com.aoeai.spin.accelerator.themes.base;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.service.service.ServiceClassService;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class BaseServiceThemesService implements ServiceThemesService {

    private IBaseRule baseRule;

    private PersistentRule persistentRule;

    private ServiceRule serviceRule;

    private WebRule webRule;

    @Resource
    private ServiceClassService serviceClassService;

    @PostConstruct
    private void init(){
        String yamlName = "/themes/base/config.yml";
        baseRule = RuleFactory.buildBaseRule(yamlName);
        persistentRule = RuleFactory.buildPersistentRule(yamlName);
        serviceRule = RuleFactory.buildServiceRule(yamlName);
        webRule = RuleFactory.buildWebRule(yamlName);
    }

    @Override
    public ServiceClass getServiceClass(String tableName) {
        return serviceClassService.buildServiceClass(tableName, baseRule, persistentRule, serviceRule, webRule);
    }

    @Override
    public void createServiceClassFile(String tableName) throws IOException, TemplateException {
        serviceClassService.createServiceClassFile(getServiceClass(tableName));
    }
}
