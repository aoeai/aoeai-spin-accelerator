package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import org.apache.commons.lang3.StringUtils;

/**
 * 规则工厂
 * @author aoe
 * @date 2020/6/7
 */
public class RuleFactory {

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
        return new IPersistentRule() {
            @Override
            public String poPackageSuffix() {
                return StringUtils.isBlank(grConfig.getPoPackageName()) ? "" : grConfig.getPoPackageName();
            }

            @Override
            public String poClassNameSuffix() {
                return StringUtils.isBlank(grConfig.getPoClassNameSuffix()) ? "" : grConfig.getPoClassNameSuffix();
            }

            @Override
            public String tablePrefixFilter() {
                return grConfig.getTablePrefixFilter();
            }
        };
    }
}
