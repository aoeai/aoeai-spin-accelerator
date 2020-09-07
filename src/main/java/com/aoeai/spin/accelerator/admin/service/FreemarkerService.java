package com.aoeai.spin.accelerator.admin.service;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FreemarkerService {

    /**
     * freemarker配置
     */
    private static Configuration FREEMARKER_CFG;

    @PostConstruct
    private void init() {
        FREEMARKER_CFG = new Configuration(Configuration.getVersion());
        FREEMARKER_CFG.setTemplateLoader(new ClassTemplateLoader(getClass().getClassLoader(), "themes"));
        FREEMARKER_CFG.setObjectWrapper(new DefaultObjectWrapper(Configuration.getVersion()));
    }

    public Template getTemplate(String name) throws IOException {
        return FREEMARKER_CFG.getTemplate(name);
    }
}
