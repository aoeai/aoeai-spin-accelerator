package com.aoeai.spin.accelerator.generate.service.service;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.generate.web.service.WebService;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import freemarker.template.TemplateException;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class ServiceClassServiceImpl implements ServiceClassService {

    @Resource
    private DBService dbService;

    @Resource
    private FreemarkerService freemarkerService;

    @Resource
    private PersistentService persistentService;

    @Resource
    private WebService webService;

    @Override
    public ServiceClass buildServiceClass(String tableName, IBaseRule baseRule, PersistentRule persistentRule,
                                          ServiceRule serviceRule, WebRule webRule) {
        ServiceClass serviceClass = new ServiceClass();
        serviceClass.setMapperService(persistentService.buildMapperService(tableName, baseRule, persistentRule));
        serviceClass.setMapperServiceVariable(WordUtils.uncapitalize(serviceClass.getMapperService().getClassName()));
        Table table = dbService.getTable(tableName);
        serviceClass.setPackageName(ClassTools.buildPackageName(baseRule.rootPackageName(), serviceRule.servicePackageSuffix()));
        serviceClass.setClassName(ClassTools.buildClassName(table.getName(), persistentRule.tablePrefixFilter(), serviceRule.serviceSuffix()));
        serviceClass.setClassComment(table.getComment() + "服务");

        serviceClass.setPageListQO(webService.buildPageListQO(tableName, baseRule, persistentRule, webRule));
        serviceClass.setVo(webService.buildVO(tableName, baseRule, persistentRule, webRule));

        serviceClass.setTemplates(StrUtil.format("{}/service/service.ftl", baseRule.themes()));
        String fileName = StrUtil.format("{}{}.java",
                serviceRule.servicePath(), serviceClass.getClassName());
        serviceClass.setFile(new File(fileName));

        return serviceClass;
    }

    @Override
    public void createServiceClassFile(ServiceClass serviceClass) throws IOException, TemplateException {
        FileTools.buildFile(serviceClass.getFile(), freemarkerService.getTemplate(serviceClass.getTemplates()), serviceClass);
    }
}
