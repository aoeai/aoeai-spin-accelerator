package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.themes.*;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 生成文件
 * @author aoe
 * @date 2020/6/8
 */
@RestController
public class GenerateController {

    @Resource
    private ThemeTools themeTools;

    @RequestMapping("createAll")
    public String createPO(String tableName, String theme) throws IOException, TemplateException {
        ThemeFactory themeFactory = themeTools.themeFactory(theme);
        // dao
        POThemesService poThemesService = themeFactory.buildPOThemesService();
        poThemesService.createPOFile(tableName);
        poThemesService.createMapperClassFile(tableName);
        poThemesService.createMapperXmlFile(tableName);
        poThemesService.createMapperServiceFile(tableName);
        poThemesService.createMapperServiceImplFile(tableName);

        // service
        ServiceThemesService serviceThemesService = themeFactory.buildServiceThemesService();
        serviceThemesService.createServiceClassFile(tableName);

        ServiceThemesService iServiceThemesService = themeFactory.buildIServiceThemesService();
        if (iServiceThemesService != null) {
            iServiceThemesService.createServiceClassFile(tableName);
        }

        // web
        WebThemesService webThemesService = themeFactory.buildWebThemesService();
        webThemesService.createPageListQOFile(tableName);
        webThemesService.createVOFile(tableName);
        webThemesService.createFormFile(tableName);
        webThemesService.createControllerFile(tableName);

        WebThemesService iwebThemesService = themeFactory.buildIWebThemesService();
        if (iwebThemesService != null) {
            iwebThemesService.createControllerFile(tableName);
        }

        // Test
        TestThemesService testThemesService = themeFactory.buildTestThemesService();
        testThemesService.createControllerTestFile(tableName);

        return "ok";
    }

    @RequestMapping("create")
    public String create(String tableName, String type, String theme) throws IOException, TemplateException {
        ThemeFactory themeFactory = themeTools.themeFactory(theme);
        POThemesService poThemesService = themeFactory.buildPOThemesService();
        ServiceThemesService serviceThemesService = themeFactory.buildServiceThemesService();
        ServiceThemesService iServiceThemesService = themeFactory.buildIServiceThemesService();
        WebThemesService iwebThemesService = themeFactory.buildIWebThemesService();
        WebThemesService webThemesService = themeFactory.buildWebThemesService();
        TestThemesService testThemesService = themeFactory.buildTestThemesService();

        switch (type) {
            // dao
            case "po":
                poThemesService.createPOFile(tableName);
                break;
                case "mapperClass":
                    poThemesService.createMapperClassFile(tableName);
                break;
            case "mapperXml":
                poThemesService.createMapperXmlFile(tableName);
                break;
                case "mapperService":
                    poThemesService.createMapperServiceFile(tableName);
                break;
                case "mapperServiceImpl":
                    poThemesService.createMapperServiceImplFile(tableName);
                break;

            // service
            case "service":
                serviceThemesService.createServiceClassFile(tableName);
                break;
            case "Iservice":
                if (iServiceThemesService == null) {
                    return "没有iServiceThemesService模板";
                }
                iServiceThemesService.createServiceClassFile(tableName);
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
            case "Icontroller":
                if (iwebThemesService == null) {
                    return "没有iwebThemesService模板";
                }
                iwebThemesService.createControllerFile(tableName);
                break;
            case "controller":
                webThemesService.createControllerFile(tableName);
                break;

            // Test
            case "controllerTest":
                testThemesService.createControllerTestFile(tableName);
                break;

            default:
                return "没有选中生成对象";
        }

        return "ok";
    }
}
