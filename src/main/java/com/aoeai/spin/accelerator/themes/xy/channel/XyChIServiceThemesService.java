package com.aoeai.spin.accelerator.themes.xy.channel;

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
 * Service接口模板
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class XyChIServiceThemesService implements ServiceThemesService {

    @Resource
    private ServiceClassService serviceClassService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/channel/config.yml";
    }

    @Override
    public ServiceClass getServiceClass(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        ServiceRule serviceRule = RuleFactory.buildServiceRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        ServiceClass serviceClass = serviceClassService.buildServiceClass(tableName, baseRule, persistentRule, serviceRule, webRule);
        // 自定义模板
        serviceClass.setTemplates("xy/channel/service/i_service.ftl");
        return serviceClass;
    }

    @Override
    public void createServiceClassFile(String tableName) throws IOException, TemplateException {
        serviceClassService.createServiceClassFile(getServiceClass(tableName));
    }
}
