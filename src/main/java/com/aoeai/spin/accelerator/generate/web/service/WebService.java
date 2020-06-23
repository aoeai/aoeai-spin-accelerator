package com.aoeai.spin.accelerator.generate.web.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
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
}
