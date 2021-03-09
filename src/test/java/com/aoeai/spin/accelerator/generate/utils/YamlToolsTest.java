package com.aoeai.spin.accelerator.generate.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.util.Map;

public class YamlToolsTest {

    private static String BASE_PATH = FileTools.getTestResourcesFilePath("test-yaml-base.yaml");
    private static String CONFIG_PATH = FileTools.getTestResourcesFilePath("test-yaml-config.yaml");

    @Test
    public void testGetYaml() throws FileNotFoundException {
        String yamlStr = YamlTools.mergeYaml(BASE_PATH);
        Yaml yaml = new Yaml();

        Map<String, Object> map = yaml.loadAs(yamlStr, Map.class);
        Assert.assertEquals(map.get("model"), "video");
        Assert.assertEquals(map.get("numPlus"), "1+");
        Assert.assertEquals(map.get("pkg"), "com.video");
        Assert.assertEquals(map.get("path"), "aoe/video");
        Assert.assertEquals(map.get("path1"), "video/1/2video/video.video");
        Assert.assertEquals(map.get("packagePrefix"), "com.dashang.vod.video");
        Assert.assertEquals(Integer.valueOf("" + map.get("num")), Integer.valueOf(1));
    }

    @Test
    public void testGetYaml1() throws FileNotFoundException {
        String yamlStr = YamlTools.mergeYaml(BASE_PATH, CONFIG_PATH);
        Yaml yaml = new Yaml();

        Map<String, Object> map = yaml.loadAs(yamlStr, Map.class);
        Assert.assertEquals(map.get("model"), "video");
        Assert.assertEquals(map.get("numPlus"), "1+");
        Assert.assertEquals(map.get("pkg"), "com.video");
        Assert.assertEquals(map.get("path"), "aoe/video");
        Assert.assertEquals(map.get("packagePrefix"), "com.dashang.vod.video");
        Assert.assertEquals(Integer.valueOf("" + map.get("num")), Integer.valueOf(1));
        Assert.assertEquals(map.get("pkgName"), "com.pkg.video");
        Assert.assertEquals(map.get("url"), "https//:www.google.com");
        Assert.assertEquals(map.get("filePath"), "/vod/video/model/db/");
    }

}