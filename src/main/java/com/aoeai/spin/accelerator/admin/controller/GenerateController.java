package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.IGenerateProperty;
import com.aoeai.spin.accelerator.generate.utils.FileTools;
import com.aoeai.spin.accelerator.themes.frame.ThemeFactory;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 生成文件
 * @author aoe
 * @date 2020/6/8
 */
@RestController
public class GenerateController {

    @Resource
    private ThemeFactory themeFactory;

    @Resource
    private FreemarkerService freemarkerService;

    @RequestMapping("createAll")
    public String createPO(String tableName, String theme) throws IOException, TemplateException {
        List<Module> modules = themeFactory.getModules(theme);
        for (Module module : modules) {
            IGenerateProperty builder = module.getFactory().build(tableName);
            FileTools.buildFile(builder.getFile(), freemarkerService.getTemplate(builder.getTemplates()), builder);
        }
        return "ok";
    }

    @RequestMapping("create")
    public String create(String tableName, String type, String theme) throws IOException, TemplateException {
        List<Module> modules = themeFactory.getModules(theme);
        for (Module module : modules) {
            if (type.equals(module.getCode())) {
                IGenerateProperty builder = module.getFactory().build(tableName);
                FileTools.buildFile(builder.getFile(), freemarkerService.getTemplate(builder.getTemplates()), builder);
                return "ok";
            }
        }
        return "fail";
    }
}
