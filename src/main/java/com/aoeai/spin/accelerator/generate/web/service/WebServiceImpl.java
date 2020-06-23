package com.aoeai.spin.accelerator.generate.web.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class WebServiceImpl implements WebService {

    @Resource
    private DBService dbService;

    @Resource
    private FreemarkerService freemarkerService;

    @Resource
    private PersistentService persistentService;

    @Override
    public PageListQO buildPageListQO(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule) {
        PageListQO qo = new PageListQO();
        qo.setPo(persistentService.buildPO(tableName, baseRule, persistentRule));
        Table table = dbService.getTable(tableName);
        qo.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), webRule.pageListQOPackageSuffix()));
        qo.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), webRule.pageListQOSuffix()));
        qo.setClassComment(table.getComment());

        qo.setTemplates(StrUtil.format("{}/web/request/page_list_qo.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                webRule.pageListQOPath(), qo.getClassName());
        qo.setFile(new File(fileName));

        return qo;
    }

    @Override
    public void createPageListQOFile(PageListQO qo) throws IOException, TemplateException {
        FileTools.buildFile(qo.getFile(), freemarkerService.getTemplate(qo.getTemplates()), qo);
    }

    @Override
    public VO buildVO(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule) {
        VO vo = new VO();
        vo.setPo(persistentService.buildPO(tableName, baseRule, persistentRule));
        Table table = dbService.getTable(tableName);
        vo.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), webRule.voPackageSuffix()));
        vo.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), webRule.voSuffix()));
        vo.setClassComment(table.getComment());

        vo.setTemplates(StrUtil.format("{}/web/response/vo.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                webRule.voPath(), vo.getClassName());
        vo.setFile(new File(fileName));

        return vo;
    }

    @Override
    public void createVOFile(VO vo) throws IOException, TemplateException {
        FileTools.buildFile(vo.getFile(), freemarkerService.getTemplate(vo.getTemplates()), vo);
    }
}
