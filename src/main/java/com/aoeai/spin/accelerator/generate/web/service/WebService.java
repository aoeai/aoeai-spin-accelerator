package com.aoeai.spin.accelerator.generate.web.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.web.bean.Controller;
import com.aoeai.spin.accelerator.generate.web.bean.Form;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Web 层服务
 */
public interface WebService {

    /**
     * 创建页面查询类
     * @param tableName
     * @param baseRule
     * @param webRule
     * @return
     */
    PageListQO buildPageListQO(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule);

    /**
     * 创建页面查询类文件
     * @param qo
     */
    void createPageListQOFile(PageListQO qo) throws IOException, TemplateException;

    /**
     * 创建VO类
     * @param tableName
     * @param baseRule
     * @param webRule
     * @return
     */
    VO buildVO(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule);

    /**
     * 创建VO类文件
     * @param vo
     */
    void createVOFile(VO vo) throws IOException, TemplateException;

    /**
     * 创建Form 表单类
     * @param tableName
     * @param baseRule
     * @param webRule
     * @return
     */
    Form buildForm(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule);

    /**
     * 创建Form 表单类文件
     * @param form
     */
    void createFormFile(Form form) throws IOException, TemplateException;

    /**
     * 创建控制器类
     * @param tableName
     * @param baseRule
     * @param webRule
     * @return
     */
    Controller buildController(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                               ServiceRule serviceRule, WebRule webRule);

    /**
     * 创建控制器类文件
     * @param controller
     */
    void createControllerFile(Controller controller) throws IOException, TemplateException;
}
