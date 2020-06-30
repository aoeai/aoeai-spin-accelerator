package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.test.rule.TestRule;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
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
     * 模块路径符号
     */
    private final static String SYMBOL_MODEL_PATH = "\\$MODEL_PATH";

    /**
     *
     * @param yamlName yaml文件名称 "/default-generate-rule-config.yml"
     * @param tableName 表名
     * @return
     */
    public static IBaseRule buildBaseRule(String yamlName, String tableName) {
        GenerateRuleConfig grConfig = ConfigTools.getGenerateRuleConfig(yamlName);
        return new IBaseRule() {
            @Override
            public GenerateRuleConfig grConfig() {
                return grConfig;
            }

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

            @Override
            public String tableName() {
                return tableName;
            }

            @Override
            public String tablePrefixFilter() {
                return grConfig.getTablePrefixFilter();
            }

            @Override
            public String yamlName() {
                return yamlName;
            }

            @Override
            public String modelName() {
                return ClassTools.getModelName(tableName(), tablePrefixFilter());
            }
        };
    }

    /**
     *
     * @param baseRule
     * @return
     */
    public static PersistentRule buildPersistentRule(IBaseRule baseRule){
        GenerateRuleConfig grConfig = baseRule.grConfig();
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
                return StringUtils.isBlank(grConfig.getMapperXmlPath()) ? ""
                        : replaceRootPath(grConfig.getMapperXmlPath(), baseRule);
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
     * @param baseRule
     * @return
     */
    public static ServiceRule buildServiceRule(IBaseRule baseRule) {
        GenerateRuleConfig grConfig = baseRule.grConfig();
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

    public static WebRule buildWebRule(IBaseRule baseRule) {
        GenerateRuleConfig grConfig = baseRule.grConfig();
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

            @Override
            public String formPath() {
                return StringUtils.isBlank(grConfig.getFormPath()) ? ""
                        : replaceRootPath(grConfig.getFormPath(), baseRule);
            }

            @Override
            public String formPackageSuffix() {
                return getPackageSuffix(formPath(), baseRule);
            }

            @Override
            public String formSuffix() {
                return StringUtils.isBlank(grConfig.getFormClassNameSuffix()) ? "" : grConfig.getFormClassNameSuffix();
            }

            @Override
            public String controllerPath() {
                return StringUtils.isBlank(grConfig.getControllerPath()) ? ""
                        : replaceRootPath(grConfig.getControllerPath(), baseRule);
            }

            @Override
            public String controllerPackageSuffix() {
                return getPackageSuffix(controllerPath(), baseRule);
            }

            @Override
            public String controllerSuffix() {
                return StringUtils.isBlank(grConfig.getControllerClassNameSuffix()) ? "" : grConfig.getControllerClassNameSuffix();
            }
        };
    }

    public static TestRule buildTestRule(IBaseRule baseRule){
        GenerateRuleConfig grConfig = baseRule.grConfig();
        return new TestRule() {
            @Override
            public String controllerTestPath() {
                return StringUtils.isBlank(grConfig.getControllerTestPath()) ? ""
                        : replaceRootPath(grConfig.getControllerTestPath(), baseRule).replaceFirst("main", "test");
            }

            @Override
            public String controllerTestPackageSuffix() {
                return getPackageSuffix(controllerTestPath().replaceFirst("test", "main"), baseRule).replaceFirst("main", "test");
            }

            @Override
            public String controllerTestSuffix() {
                return StringUtils.isBlank(grConfig.getControllerTestClassNameSuffix()) ? "" : grConfig.getControllerTestClassNameSuffix();
            }

            @Override
            public String hostTest() {
                return grConfig.getHostTest();
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
        return absolutePath
                .replaceFirst(SYMBOL_ROOT_PATH, baseRule.generatorRootPath())
                .replaceFirst(SYMBOL_MODEL_PATH, baseRule.modelName())
                .replaceAll("//", "/");
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
