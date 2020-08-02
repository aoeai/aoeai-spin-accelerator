package com.aoeai.spin.accelerator.themes.xy.wazhima;

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
public class XyWzmServiceThemesService implements ServiceThemesService {

    @Resource
    private ServiceClassService serviceClassService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/wazhima/config.yml";
    }

    @Override
    public ServiceClass getServiceClass(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        ServiceRule serviceRule = RuleFactory.buildServiceRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        return serviceClassService.buildServiceClass(tableName, baseRule, persistentRule, serviceRule, webRule);
    }

    @Override
    public void createServiceClassFile(String tableName) throws IOException, TemplateException {
        serviceClassService.createServiceClassFile(getServiceClass(tableName));
    }
}
