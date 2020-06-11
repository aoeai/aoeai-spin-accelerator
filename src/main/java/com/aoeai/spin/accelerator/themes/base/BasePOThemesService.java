package com.aoeai.spin.accelerator.themes.base;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.themes.POThemesService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/7
 */
@Service
public class BasePOThemesService implements POThemesService {

    private IBaseRule baseRule;

    private IPersistentRule persistentRule;

    @Resource
    private PersistentService persistentService;

    @PostConstruct
    private void init(){
        String yamlName = "/themes/base/config.yml";
        baseRule = RuleFactory.buildBaseRule(yamlName);
        persistentRule = RuleFactory.buildPersistentRule(yamlName);
    }

    @Override
    public PO getPO(String tableName) {
        return persistentService.buildPO(tableName, baseRule, persistentRule);
    }

    @Override
    public void createPOFile(String tableName) throws IOException, TemplateException {
        persistentService.createPOFile(getPO(tableName));
    }

}
