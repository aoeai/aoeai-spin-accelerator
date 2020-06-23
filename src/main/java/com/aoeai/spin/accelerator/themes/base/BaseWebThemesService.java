package com.aoeai.spin.accelerator.themes.base;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
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

    private IBaseRule baseRule;

    private PersistentRule persistentRule;

    private WebRule webRule;

    @Resource
    private WebService webService;

    @PostConstruct
    private void init(){
        String yamlName = "/themes/base/config.yml";
        baseRule = RuleFactory.buildBaseRule(yamlName);
        persistentRule = RuleFactory.buildPersistentRule(yamlName);
        webRule = RuleFactory.buildWebRule(yamlName);
    }


    @Override
    public PageListQO getPageListQO(String tableName) {
        return webService.buildPageListQO(tableName, baseRule, persistentRule, webRule);
    }

    @Override
    public void createPageListQOFile(String tableName) throws IOException, TemplateException {
        webService.createPageListQOFile(getPageListQO(tableName));
    }

    @Override
    public VO getVO(String tableName) {
        return webService.buildVO(tableName, baseRule, persistentRule, webRule);
    }

    @Override
    public void createVOFile(String tableName) throws IOException, TemplateException {
        webService.createVOFile(getVO(tableName));
    }
}
