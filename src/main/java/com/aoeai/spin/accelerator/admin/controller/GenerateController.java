package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
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

    @RequestMapping("createPO")
    public String createPO(String tableName) throws IOException, TemplateException {
        poThemesService.createPOFile(tableName);
        poThemesService.createMapperClassFile(tableName);
        poThemesService.createMapperXmlFile(tableName);
        poThemesService.createMapperServiceFile(tableName);
        poThemesService.createMapperServiceImplFile(tableName);

        serviceThemesService.createServiceClassFile(tableName);

        webThemesService.createPageListQOFile(tableName);
        webThemesService.createVOFile(tableName);

        return "ok";
    }
}
