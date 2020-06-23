package com.aoeai.spin.accelerator.themes.base;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.web.bean.Controller;
import com.aoeai.spin.accelerator.generate.web.bean.Form;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.generate.web.service.WebService;
import com.aoeai.spin.accelerator.themes.WebThemesService;
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
public class BaseWebThemesService implements WebThemesService {

    @Resource
    private WebService webService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/base/config.yml";
    }


    @Override
    public PageListQO getPageListQO(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        return webService.buildPageListQO(tableName, baseRule, persistentRule, webRule);
    }

    @Override
    public void createPageListQOFile(String tableName) throws IOException, TemplateException {
        webService.createPageListQOFile(getPageListQO(tableName));
    }

    @Override
    public VO getVO(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        return webService.buildVO(tableName, baseRule, persistentRule, webRule);
    }

    @Override
    public void createVOFile(String tableName) throws IOException, TemplateException {
        webService.createVOFile(getVO(tableName));
    }

    @Override
    public Form getForm(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        return webService.buildForm(tableName, baseRule, persistentRule, webRule);
    }

    @Override
    public void createFormFile(String tableName) throws IOException, TemplateException {
        webService.createFormFile(getForm(tableName));
    }

    @Override
    public Controller getController(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        ServiceRule serviceRule = RuleFactory.buildServiceRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        return webService.buildController(tableName, baseRule, persistentRule, serviceRule, webRule);
    }

    @Override
    public void createControllerFile(String tableName) throws IOException, TemplateException {
        webService.createControllerFile(getController(tableName));
    }
}
