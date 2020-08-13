package com.aoeai.spin.accelerator.themes.xy.channel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.web.bean.*;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.generate.web.service.WebService;
import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
import com.aoeai.spin.accelerator.themes.WebThemesService;
import freemarker.template.TemplateException;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 控制器接口模板
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class XyChIWebThemesService implements WebThemesService {

    @Resource
    private WebService webService;

    @Resource
    private POThemesService xyChPOThemesService;

    @Resource
    private ServiceThemesService xyChIServiceThemesService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/channel/config.yml";
    }

    @Override
    public PageListQO getPageListQO(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        PageListQO pageListQO = webService.buildPageListQO(tableName, baseRule, persistentRule, webRule);

        // 个性化
        pageListQO.setClassName(pageListQO.getClassName().replaceFirst("Channel", "Ch"));
        String fileName = baseRule.generatorRootPath()
                + StrUtil.format("{}{}.java", "common/pojo/qo/", pageListQO.getClassName());
        pageListQO.setFile(new File(fileName));
        pageListQO.setPackageName("com.starbuds.server.api.channel.common.pojo.qo");
        pageListQO.setPo(xyChPOThemesService.getPO(tableName));

        return pageListQO;
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
        VO vo = webService.buildVO(tableName, baseRule, persistentRule, webRule);

        // 个性化
        String fileName = baseRule.generatorRootPath()
                + StrUtil.format("{}{}.java", "common/pojo/vo/", vo.getClassName());
        vo.setFile(new File(fileName));
        vo.setPackageName("com.starbuds.server.api.channel.common.pojo.vo");
        vo.setPo(xyChPOThemesService.getPO(tableName));
        return vo;
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
        Form form = webService.buildForm(tableName, baseRule, persistentRule, webRule);

        // 个性化
        String fileName = baseRule.generatorRootPath()
                + StrUtil.format("{}{}.java", "common/pojo/form/", form.getClassName());
        form.setFile(new File(fileName));
        form.setPackageName("com.starbuds.server.api.channel.common.pojo.form");
        form.setPo(xyChPOThemesService.getPO(tableName));

        // 删除多余的包
        Set<String> importList = form.getImportList();
        if (!CollectionUtil.isEmpty(importList)) {
            importList.remove("java.math.BigInteger");
            importList.remove("javax.validation.constraints.NotBlank");
            importList.remove("javax.validation.constraints.NotNull");
        }

        for (FormField field : form.getFieldList()) {
            if ("BigInteger".equals(field.getClassShortName())) {
                field.setClassShortName(JavaTypeEnum.LONG.shortName());
                field.setClassFullName(JavaTypeEnum.LONG.fullName());
            }

            // 替换表单验证标签
            List<String> checkList = field.getCheckTagList();
            if (CollectionUtil.isEmpty(checkList)) {
                continue;
            }
            List<String> xyCheckList = new ArrayList<>(checkList.size());
            for (String tag : checkList) {
                String notNull = "@NotNull";
                if (tag.startsWith(notNull)) {
                    tag = tag.replaceFirst(notNull, "@XyNotNull")
                    .replaceFirst("message", "msg");
                }

                String notBlank = "@NotBlank";
                if (tag.startsWith(notBlank)) {
                    tag = tag.replaceFirst(notBlank, "@XyNotBlank")
                            .replaceFirst("message", "msg");
                }
                xyCheckList.add(tag);
            }
            field.setCheckTagList(xyCheckList);
        }

        return form;
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
        Controller controller = webService.buildController(tableName, baseRule, persistentRule, serviceRule, webRule);

        // 个性化
        String fileName = baseRule.generatorRootPath()
                + StrUtil.format("{}{}.java", "v1/facade/", controller.getClassName());
        controller.setFile(new File(fileName));
        controller.setPackageName("com.starbuds.server.api.channel.v1.facade");
        controller.setPo(xyChPOThemesService.getPO(tableName));
        controller.setServiceClass(xyChIServiceThemesService.getServiceClass(tableName));
        controller.setServiceClassVariable(WordUtils.uncapitalize(controller.getServiceClass().getClassName()));
        controller.setForm(getForm(tableName));
        controller.setPageListQO(getPageListQO(tableName));
        controller.setTemplates("xy/channel/web/i_controller.ftl");
        return controller;
    }

    @Override
    public void createControllerFile(String tableName) throws IOException, TemplateException {
        webService.createControllerFile(getController(tableName));
    }
}
