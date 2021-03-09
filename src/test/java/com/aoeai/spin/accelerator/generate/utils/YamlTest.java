package com.aoeai.spin.accelerator.generate.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.regex.Matcher;

/**
 * @author aoe
 * @date 2021/3/1
 */
public class YamlTest {

    @Test
    public void yamlTest(){
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new FileSystemResource("src/test/resources/test.yaml"));
        Properties properties = yaml.getObject();
        String model = (String) properties.get("model");
        String pkg = (String) properties.get("pkg");

        System.out.println("model " + model);
        System.out.println("pkg " + pkg);
        System.out.println("pkg " + pkg.replaceAll(Matcher.quoteReplacement("$[{]model}"), "image"));
    }

}
