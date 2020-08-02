package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.admin.service.FreemarkerService;
import com.aoeai.spin.accelerator.generate.common.ITemplates;
import com.aoeai.spin.accelerator.refining.db.service.DBService;
import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ThemeFactory;
import com.aoeai.spin.accelerator.themes.constant.ThemeTypeEnum;
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
 * @date 2020/6/5
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
     * @param model
     * @return 数据库列表
     */
    @GetMapping("/")
    public String index(@RequestParam(required = false) String tableName, @RequestParam(required = false) String theme, Model model){
        List tableList =  dbService.getTableList(tableName);
        model.addAttribute("tableList", tableList);
        model.addAttribute("themeList", ThemeTypeEnum.values());

        // 搜索条件
        model.addAttribute("tableName", tableName);
        model.addAttribute("themeHis", theme);
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
        model.addAttribute("tableName", tableName);
        model.addAttribute("theme", ThemeTypeEnum.toEnum(theme));
        return "web/table-detail";
    }

    /**
     * 生成预览
     * @param tableName
     * @param type
     * @param theme
     * @param model
     * @return
     */
    @GetMapping("/preview")
    public String preview(String tableName, String type, String theme, Model model) throws IOException, TemplateException {
        model.addAttribute("tableName", tableName);
        model.addAttribute("theme", ThemeTypeEnum.toEnum(theme));

        ITemplates content = null;
        POThemesService poThemesService = themeFactory.buildPOThemesService(theme);
        switch (type) {
            // dao
            case "po":
                content = poThemesService.getPO(tableName);
                break;
            case "mapperClass":
                content = poThemesService.getMapperClass(tableName);
                break;
            case "mapperXml":
                content = poThemesService.getMapperXml(tableName);
                break;
            case "mapperService":
                content = poThemesService.getMapperService(tableName);
                break;
            case "mapperServiceImpl":
                content = poThemesService.getMapperServiceImpl(tableName);
                break;

            /*// service
            case "service":
                serviceThemesService.createServiceClassFile(tableName);
                break;

            // web
            case "pageListQO":
                webThemesService.createPageListQOFile(tableName);
                break;
            case "vo":
                webThemesService.createVOFile(tableName);
                break;
            case "form":
                webThemesService.createFormFile(tableName);
                break;
            case "controller":
                webThemesService.createControllerFile(tableName);
                break;

            // Test
            case "controllerTest":
                testThemesService.createControllerTestFile(tableName);
                break;*/
        }

        Template template = freemarkerService.getTemplate(content.getTemplates());
        StringWriter writer = new StringWriter();
        template.process(content, writer);
        model.addAttribute("writer", writer.toString());

        return "web/preview";
    }
}
