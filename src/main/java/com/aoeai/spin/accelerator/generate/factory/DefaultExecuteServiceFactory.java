package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;

/**
 * (默认实现)执行生成的服务类工厂
 */
@Deprecated
public class DefaultExecuteServiceFactory  {

    private GenerateRuleConfig grConfig;

    public DefaultExecuteServiceFactory(GenerateRuleConfig grConfig) {
        this.grConfig = grConfig;
    }

    /**
     * 获得持久对象服务实现类
     * @return 持久对象服务实现类
     */
    /*private PersistentService buildPersistentService(DBTableService dbTableService){
        IBaseRule baseRule = new IBaseRule() {
            @Override
            public String rootPackageName() {
                return grConfig.getRootPackageName();
            }

            @Override
            public String generatorRootPath() {
                return grConfig.getGeneratorRootPath();
            }
        };
        IPersistentRule persistentRule = new IPersistentRule() {
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

        return new PersistentServiceImpl(dbTableService, baseRule, persistentRule);
    }*/

}
