package com.aoeai.spin.accelerator.admin.service;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class FreemarkerService {

    /**
     * freemarker配置
     */
    private static Configuration FREEMARKER_CFG;

    @PostConstruct
    private void init() throws IOException {
        FREEMARKER_CFG = new Configuration(Configuration.getVersion());
        FREEMARKER_CFG.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")
                + "/src/main/resources/themes/"));
        FREEMARKER_CFG.setObjectWrapper(new DefaultObjectWrapper(Configuration.getVersion()));
    }

    public Template getTemplate(String name) throws IOException {
        return FREEMARKER_CFG.getTemplate(name);
    }
}
