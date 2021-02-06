package com.aoeai.spin.accelerator.generate.utils;

import cn.hutool.core.util.StrUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class FileTools {

    private static final String UTF_8 = "UTF-8";

    /**
     * 根据模板生成文件
     * @param file
     * @param template
     * @param content
     * @throws IOException
     * @throws TemplateException
     */
    public static void buildFile(File file, Template template, Object content) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(content, writer);

        FileUtils.writeStringToFile(file,
                writer.toString(), UTF_8);
        log.info("生成文件 {}", file.getAbsoluteFile());
    }

    /**
     * 拷贝文件
     * @param srcDir 源文件
     * @param destDir 目标文件
     * @throws IOException
     */
    public static void copy(final File srcDir, final File destDir) throws IOException {
        FileUtils.copyDirectory(srcDir, destDir);
    }

    public static String readFileToString(File file) throws IOException {
        return FileUtils.readFileToString(file, UTF_8);
    }

    /**
     * 获得src/main/resources/目录下的文件路径
     * @param filePath
     * @return
     */
    public static String getMainResourcesFilePath(String filePath){
        return getRootPath() + "/src/main/resources/" + filePath;
    }

    /**
     * 获得项目根目录
     * @return
     */
    private static String getRootPath(){
        File file = new File(FileTools.class.getResource("/").getPath());
        String rootPath = file.getParentFile().getParentFile().getPath();
        return rootPath;
    }

    public static void main(String[] args) throws IOException {
        /*String path = "/Users/aoe/IdeaProjects/paqu/wazhima-test/src/test/java/com/wazhima/mgt/statistics/MgtStatisticsest.java";
        File file = new File(path);
        List<String> lines = FileUtils.readLines(file, CHARSET);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(StrUtil.format("{} {}", i + 1, lines.get(i)));
        }*/

        String path = "/Users/aoe/IdeaProjects/paqu/wazhima-test/src/test/java/com";
        File dir = new File(path);
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"java"}, true);
        for (File file : files) {
            List<String> lines = FileUtils.readLines(file, UTF_8);
            List<String> newLines = new ArrayList<>();
            boolean isReplace = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String tag = "// replace@90192";
                if (tag.equals(line.trim())) {
                    String fileName = file.getAbsolutePath() + file.getName();
                    System.out.println(fileName);
                    System.out.println(StrUtil.format("{} {}", i + 1, line));
                    isReplace = true;
                    String lineNew = StrUtil.format(line.replace(tag, "{}"), "// 666666");
                    newLines.add(lineNew);
                } else {
                    newLines.add(line);
                }
            }

            if (isReplace) {
                // 清空文件
                FileUtils.write(file, "", UTF_8);
                FileUtils.writeLines(file, UTF_8, newLines);
            }
        }
    }

}
