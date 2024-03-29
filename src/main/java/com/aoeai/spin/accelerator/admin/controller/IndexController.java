package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.admin.vo.TableVO;
import com.aoeai.spin.accelerator.generate.common.IGenerateProperty;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import com.aoeai.spin.accelerator.themes.frame.ThemeFactory;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * @author aoe
 * @date 2020/8/24
 */
@Controller
public class IndexController {

    @Resource
    private DBService dbService;

    @Resource
    private FreemarkerService freemarkerService;

    @Resource
    private ThemeFactory themeFactory;

    /**
     * 首页
     * @param tableName
     * @param code 模板代码
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@RequestParam(required = false) String tableName, @RequestParam(required = false) String code, Model model){
        List<TableVO> tableList =  dbService.getTableList(tableName);
        model.addAttribute("tableList", tableList);
        model.addAttribute("themeList", themeFactory.getThemeTypes());

        // 搜索条件
        model.addAttribute("tableName", tableName);
        model.addAttribute("themeHis", code);
        return "web/index";
    }

    /**
     * 数据库表详情
     * @param tableName
     * @param theme
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(String tableName, String theme, Model model) {
        model.addAttribute("modules", themeFactory.getModules(theme));
        model.addAttribute("tableName", tableName);
        model.addAttribute("theme", themeFactory.getThemeType(theme));

        return "web/table-detail";
    }

    /**
     * 生成预览
     * @param tableName
     * @param module
     * @param theme
     * @param model
     * @return
     */
    @GetMapping("/preview")
    public String preview(String tableName, String module, String theme, Model model) throws IOException, TemplateException {
        model.addAttribute("tableName", tableName);
        model.addAttribute("module", module);
        model.addAttribute("theme", themeFactory.getThemeType(theme));

        IGenerateProperty content = themeFactory.buildModule(theme, module, tableName);
        Template template = freemarkerService.getTemplate(content.getTemplates());
        StringWriter writer = new StringWriter();
        template.process(content, writer);
        model.addAttribute("writer", writer.toString());
        model.addAttribute("file", content.getFile());

        return "web/preview";
    }
}
