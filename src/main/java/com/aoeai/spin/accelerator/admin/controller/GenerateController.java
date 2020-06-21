package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.themes.POThemesService;
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

    @RequestMapping("createPO")
    public String createPO(String tableName) throws IOException, TemplateException {
        poThemesService.createPOFile(tableName);
        poThemesService.createMapperClassFile(tableName);
        poThemesService.createMapperXmlFile(tableName);
        poThemesService.createMapperServiceFile(tableName);
        return "ok";
    }
}
