package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 规则工厂
 * @author aoe
 * @date 2020/6/7
 */
public class RuleFactory {

    /**
     * 根路径符号
     */
    private final static String SYMBOL_ROOT_PATH = "\\$ROOT_PATH";

    /**
     *
     * @param yamlName yaml文件名称 "/default-generate-rule-config.yml"
     * @return
     */
    public static IBaseRule buildBaseRule(String yamlName) {
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        return new IBaseRule() {
            @Override
            public String rootPackageName() {
                return grConfig.getRootPackageName();
            }

            @Override
            public String generatorRootPath() {
                String path = grConfig.getGeneratorRootPath();
                if (StringUtils.isBlank(path)) {
                    path = System.getProperty("user.dir") + "/target/GENERATE/";
                }
                return path;
            }

            @Override
            public String themes() {
                return grConfig.getThemes();
            }
        };
    }

    /**
     *
     * @param yamlName
     * @return
     */
    public static IPersistentRule buildPersistentRule(String yamlName){
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        IBaseRule baseRule = buildBaseRule(yamlName);
        return new IPersistentRule() {
            @Override
            public String poPath() {
                return StringUtils.isBlank(grConfig.getPoPath()) ? ""
                        : replaceRootPath(grConfig.getPoPath(), baseRule);
            }

            @Override
            public String poPackageSuffix() {
                return getPackageSuffix(poPath(), baseRule);
            }

            @Override
            public String poClassNameSuffix() {
                return StringUtils.isBlank(grConfig.getPoClassNameSuffix()) ? "" : grConfig.getPoClassNameSuffix();
            }

            @Override
            public String tablePrefixFilter() {
                return grConfig.getTablePrefixFilter();
            }

            @Override
            public String mapperPath() {
                return StringUtils.isBlank(grConfig.getMapperPath()) ? ""
                        : replaceRootPath(grConfig.getMapperPath(), baseRule);
            }

            @Override
            public String mapperPackageSuffix() {
                return getPackageSuffix(mapperPath(), baseRule);
            }

            @Override
            public String mapperClassNameSuffix() {
                return StringUtils.isBlank(grConfig.getMapperClassNameSuffix()) ? "" : grConfig.getMapperClassNameSuffix();
            }

            @Override
            public String mapperXmlPath() {
                return grConfig.getMapperXmlPath();
            }

            @Override
            public String mapperServicePath() {
                return StringUtils.isBlank(grConfig.getPoPath()) ? ""
                        : replaceRootPath(grConfig.getMapperServicePath(), baseRule);
            }

            @Override
            public String mapperServicePackageSuffix() {
                return getPackageSuffix(mapperServicePath(), baseRule);
            }

            @Override
            public String mapperServiceClassSuffix() {
                return StringUtils.isBlank(grConfig.getMapperServiceSuffix()) ? "" : grConfig.getMapperServiceSuffix();
            }
        };
    }

    /**
     * 将根路径符号替换成真实值
     * @param absolutePath 绝对地址
     * @param baseRule
     * @return
     */
    private static String replaceRootPath(String absolutePath, IBaseRule baseRule) {
        return absolutePath.replaceFirst(SYMBOL_ROOT_PATH, baseRule.generatorRootPath())
                .replaceFirst(File.separator + File.separator, File.separator);
    }

    /**
     * 获得包名后缀
     * @param absolutePath
     * @param baseRule
     * @return
     */
    private static String getPackageSuffix(String absolutePath, IBaseRule baseRule) {
        if (StringUtils.isEmpty(absolutePath)) {
            return "";
        }
        absolutePath = absolutePath.replaceFirst(baseRule.generatorRootPath(), "");
        String pathSuffix = absolutePath.replaceAll(File.separator, ".");
        return pathSuffix.substring(0, pathSuffix.length() - 1);
    }
}
