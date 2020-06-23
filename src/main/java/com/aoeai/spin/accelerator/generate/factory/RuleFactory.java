package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
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
    public static PersistentRule buildPersistentRule(String yamlName){
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        IBaseRule baseRule = buildBaseRule(yamlName);
        return new PersistentRule() {
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
                return StringUtils.isBlank(grConfig.getMapperServicePath()) ? ""
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

            @Override
            public String mapperServiceImplPath() {
                return StringUtils.isBlank(grConfig.getMapperServiceImplPath()) ? ""
                        : replaceRootPath(grConfig.getMapperServiceImplPath(), baseRule);
            }

            @Override
            public String mapperServiceImplPackageSuffix() {
                return getPackageSuffix(mapperServiceImplPath(), baseRule);
            }

            @Override
            public String mapperServiceImplClassSuffix() {
                return StringUtils.isBlank(grConfig.getMapperServiceImplSuffix()) ? "" : grConfig.getMapperServiceImplSuffix();
            }
        };
    }

    /**
     *
     * @param yamlName
     * @return
     */
    public static ServiceRule buildServiceRule(String yamlName) {
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        IBaseRule baseRule = buildBaseRule(yamlName);

        return new ServiceRule() {
            @Override
            public String servicePath() {
                return StringUtils.isBlank(grConfig.getServicePath()) ? ""
                        : replaceRootPath(grConfig.getServicePath(), baseRule);
            }

            @Override
            public String servicePackageSuffix() {
                return getPackageSuffix(servicePath(), baseRule);
            }

            @Override
            public String serviceSuffix() {
                return StringUtils.isBlank(grConfig.getServiceSuffix()) ? "" : grConfig.getServiceSuffix();
            }
        };
    }

    public static WebRule buildWebRule(String yamlName) {
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        IBaseRule baseRule = buildBaseRule(yamlName);

        return new WebRule() {
            @Override
            public String pageListQOPath() {
                return StringUtils.isBlank(grConfig.getPageListQOPath()) ? ""
                        : replaceRootPath(grConfig.getPageListQOPath(), baseRule);
            }

            @Override
            public String pageListQOPackageSuffix() {
                return getPackageSuffix(pageListQOPath(), baseRule);
            }

            @Override
            public String pageListQOSuffix() {
                return StringUtils.isBlank(grConfig.getPageListQOClassNameSuffix()) ? "" : grConfig.getPageListQOClassNameSuffix();
            }

            @Override
            public String voPath() {
                return StringUtils.isBlank(grConfig.getVoPath()) ? ""
                        : replaceRootPath(grConfig.getVoPath(), baseRule);
            }

            @Override
            public String voPackageSuffix() {
                return getPackageSuffix(voPath(), baseRule);
            }

            @Override
            public String voSuffix() {
                return StringUtils.isBlank(grConfig.getVoClassNameSuffix()) ? "" : grConfig.getVoClassNameSuffix();
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
