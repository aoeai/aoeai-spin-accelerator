package com.aoeai.spin.accelerator.generate.web.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.constant.MySQLType2JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.service.service.ServiceClassService;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.generate.web.bean.*;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import freemarker.template.TemplateException;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.aoeai.spin.accelerator.generate.utils.ClassTools.buildImportList;

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

    @Resource
    private ServiceClassService serviceClassService;

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

    @Override
    public Form buildForm(String tableName, IBaseRule baseRule, PersistentRule persistentRule, WebRule webRule) {
        Form form = new Form();
        form.setPo(persistentService.buildPO(tableName, baseRule, persistentRule));
        Table table = dbService.getTable(tableName);
        form.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), webRule.formPackageSuffix()));
        form.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), webRule.formSuffix()));
        form.setClassComment(table.getComment());
        form.setTable(table);

        // 添加效验标签
        Set<String> importList =  buildImportList(table.getColumns());
        Map<String, List<String>> checkTagListMap = new HashMap<>();
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                checkTagListMap.put(column.getName(), Collections.EMPTY_LIST);
                continue;
            }

            List<String> checkTagList = new ArrayList<>();
            JavaTypeEnum javaType = MySQLType2JavaTypeEnum.javaType(column.getType(), column.getDbMaxLength());
            String comment = column.getComment();
            if (JavaTypeEnum.STRING == javaType) {
                checkTagList.add(StrUtil.format("@NotBlank(message = \"{}不能为空\")", comment));
                importList.add("javax.validation.constraints.NotBlank");

                checkTagList.add(StrUtil.format("@Length(max = {}, message = \"{}最大长度{}个字符\")",
                        column.getDbMaxLength(), comment, column.getDbMaxLength()));
                importList.add("org.hibernate.validator.constraints.Length");
            }else {
                checkTagList.add(StrUtil.format("@NotNull(message = \"{}不能为空\")", comment));
                importList.add("javax.validation.constraints.NotNull");
            }

            checkTagListMap.put(column.getName(), checkTagList);
        }
        form.setImportList(importList);

        List<FormField> formFieldList = new ArrayList<>(table.getColumns().size());
        for (POField poField : form.getPo().getFieldList()) {
            FormField formField = new FormField();
            BeanUtils.copyProperties(poField, formField);
            formField.setCheckTagList(checkTagListMap.get(formField.getName()));
            formFieldList.add(formField);
        }
        form.setFieldList(formFieldList);

        form.setTemplates(StrUtil.format("{}/web/request/form.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                webRule.formPath(), form.getClassName());
        form.setFile(new File(fileName));

        return form;
    }

    @Override
    public void createFormFile(Form form) throws IOException, TemplateException {
        FileTools.buildFile(form.getFile(), freemarkerService.getTemplate(form.getTemplates()), form);
    }

    @Override
    public Controller buildController(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                                      ServiceRule serviceRule, WebRule webRule) {
        Controller controller = new Controller();
        controller.setPo(persistentService.buildPO(tableName, baseRule, persistentRule));
        Table table = dbService.getTable(tableName);
        controller.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), webRule.controllerPackageSuffix()));
        controller.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), webRule.controllerSuffix()));
        controller.setClassComment(table.getComment() + "控制器");

        String urlPrefix = ClassTools.getModelName(table.getName(), persistentRule.tablePrefixFilter());
        controller.setUrlPrefix(urlPrefix);

        ServiceClass serviceClass = serviceClassService.buildServiceClass(tableName, baseRule, persistentRule,
                serviceRule, webRule);
        controller.setServiceClass(serviceClass);
        String serviceClassVariable = WordUtils.uncapitalize(serviceClass.getClassName());
        controller.setServiceClassVariable(serviceClassVariable);

        controller.setForm(buildForm(tableName, baseRule, persistentRule, webRule));
        controller.setPageListQO(buildPageListQO(tableName, baseRule, persistentRule, webRule));

        controller.setTemplates(StrUtil.format("{}/web/controller.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                webRule.controllerPath(), controller.getClassName());
        controller.setFile(new File(fileName));

        return controller;
    }

    @Override
    public void createControllerFile(Controller controller) throws IOException, TemplateException {
        FileTools.buildFile(controller.getFile(), freemarkerService.getTemplate(controller.getTemplates()), controller);
    }
}
