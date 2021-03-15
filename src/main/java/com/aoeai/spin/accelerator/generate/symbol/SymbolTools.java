package com.aoeai.spin.accelerator.generate.symbol;

import com.google.common.base.Preconditions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.aoeai.spin.accelerator.generate.utils.FileTools.UTF_8;

/**
 * 印记工具类
 * @author aoe
 * @date 2021/3/15
 */
public class SymbolTools {

    public static void findSymbol(File dir, String ... extensions) throws IOException {
        Preconditions.checkNotNull(dir, "印记扫描目录不能为 null %s", dir.getAbsolutePath());
        Preconditions.checkArgument(dir.isDirectory(), "印记扫描目录不是目录类型 %s", dir.getAbsolutePath());

        Collection<File> files = FileUtils.listFiles(dir, extensions, true);
        for (File file : files) {
            getSymbol(file);
        }
    }

    private static void getSymbol(File file) throws IOException {
        String symbol = "\uD83D\uDDA4-旋转加速器";
        List<String> list = FileUtils.readLines(file, UTF_8)
                .parallelStream()
                .map(line -> {
                    if (StringUtils.contains(line, symbol)) {
                        System.out.println(line);
                        return line;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


    }
}
