package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
import com.aoeai.spin.accelerator.themes.TestThemesService;
import com.aoeai.spin.accelerator.themes.WebThemesService;
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
    private POThemesService poThemesService;

    @Resource
    private ServiceThemesService serviceThemesService;

    @Resource
    private WebThemesService webThemesService;

    @Resource
    private TestThemesService testThemesService;

    @RequestMapping("createPO")
    public String createPO(String tableName) throws IOException, TemplateException {
        // dao
        poThemesService.createPOFile(tableName);
        poThemesService.createMapperClassFile(tableName);
        poThemesService.createMapperXmlFile(tableName);
        poThemesService.createMapperServiceFile(tableName);
        poThemesService.createMapperServiceImplFile(tableName);

        // service
        serviceThemesService.createServiceClassFile(tableName);

        // web
        webThemesService.createPageListQOFile(tableName);
        webThemesService.createVOFile(tableName);
        webThemesService.createFormFile(tableName);
        webThemesService.createControllerFile(tableName);

        // Test
        testThemesService.createControllerTestFile(tableName);

        return "ok";
    }

    @RequestMapping("create")
    public String create(String tableName, String type) throws IOException, TemplateException {
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
                break;

            default:
                return "没有选中生成对象";
        }

        return "ok";
    }
}
