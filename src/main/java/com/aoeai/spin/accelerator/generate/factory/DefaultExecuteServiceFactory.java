package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.config.GenerateRuleConfig;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentService;
import com.aoeai.spin.accelerator.generate.persistent.service.PersistentServiceImpl;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfig;
import com.aoeai.spin.accelerator.refining.db.config.MysqlDbConfiguration;
import com.aoeai.spin.accelerator.refining.db.service.DBTableService;
import com.aoeai.spin.accelerator.refining.db.service.MySqlDBTableServiceImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * (默认实现)执行生成的服务类工厂
 */
public class DefaultExecuteServiceFactory implements ExecuteServiceFactory {

    private GenerateRuleConfig grConfig;

    public DefaultExecuteServiceFactory(GenerateRuleConfig grConfig) {
        this.grConfig = grConfig;
    }

    @Override
    public PersistentService buildPersistentService() {
        IDbConfig dbConf = new MysqlDbConfiguration(grConfig.getHost(), grConfig.getPort(),
                grConfig.getUser(), grConfig.getPassword(), grConfig.getDatabase());
        DBTableService dbTableService = new MySqlDBTableServiceImpl(dbConf);
        return buildPersistentService(dbTableService);
    }

    /**
     * 获得持久对象服务实现类
     * @return 持久对象服务实现类
     */
    private PersistentService buildPersistentService(DBTableService dbTableService){
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
    }

}
