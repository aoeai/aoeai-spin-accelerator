package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.constant.BuildPOServiceEnum;
import com.aoeai.spin.accelerator.generate.dao.IDaoRule;
import com.aoeai.spin.accelerator.generate.execute.BuildPOService;
import com.aoeai.spin.accelerator.generate.execute.DefaultBuildPOServiceImpl;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfiguration;
import com.aoeai.spin.accelerator.refining.db.config.MysqlDbConfiguration;
import com.aoeai.spin.accelerator.refining.db.service.DBTableService;
import com.aoeai.spin.accelerator.refining.db.service.MySqlDBTableServiceImpl;

/**
 * (默认实现)执行生成的服务类工厂
 */
public class DefaultExecuteServiceFactory implements ExecuteServiceFactory {

    // IDbConfiguration 数据库配置信息
    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名
     */
    private String database;

    // IBaseRule （生成时的）基础规则
    /**
     * 工程根路径的包名
     */
    private String rootPackageName;

    /**
     * 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
     */
    private String generatorRootPath;

    // IDaoRule （生成时的）Dao规则
    /**
     * PO(持久对象)类所在位置的包名后缀
     */
    private String poPackageSuffix;

    /**
     * 生成Java文件时需要过滤掉的表名前缀（,分割）
     */
    private String tablePrefixFilter;

    private BuildPOService buildPOService;

    private BuildPOServiceEnum buildPOServiceEnum;

    public DefaultExecuteServiceFactory(BuildPOServiceEnum buildPOServiceEnum) {
        this.buildPOServiceEnum = buildPOServiceEnum;
    }

    @Override
    public BuildPOService buildPOService() {
        IDbConfiguration dbConf = new MysqlDbConfiguration(host, port, user, password, database);
        DBTableService dbTableService = new MySqlDBTableServiceImpl(dbConf);
        return getBuildPOService(dbTableService);
    }

    /**
     * 获得持久对象服务实现类
     * @return 持久对象服务实现类
     */
    private BuildPOService getBuildPOService(DBTableService dbTableService){
        IBaseRule baseRule = new IBaseRule() {
            @Override
            public String rootPackageName() {
                return rootPackageName;
            }

            @Override
            public String generatorRootPath() {
                return generatorRootPath;
            }
        };
        IDaoRule daoRule = new IDaoRule() {
            @Override
            public String poPackageSuffix() {
                return poPackageSuffix;
            }

            @Override
            public String tablePrefixFilter() {
                return tablePrefixFilter;
            }
        };

        switch (buildPOServiceEnum) {
            case DEFAULT:
                return new DefaultBuildPOServiceImpl(dbTableService,
                        baseRule, daoRule);
        }

        return null;
    }

    public DefaultExecuteServiceFactory host(String host) {
        this.host = host;
        return this;
    }

    public DefaultExecuteServiceFactory port(String port) {
        this.port = port;
        return this;
    }

    public DefaultExecuteServiceFactory user(String user) {
        this.user = user;
        return this;
    }

    public DefaultExecuteServiceFactory password(String password) {
        this.password = password;
        return this;
    }

    public DefaultExecuteServiceFactory database(String database) {
        this.database = database;
        return this;
    }

    public DefaultExecuteServiceFactory rootPackageName(String rootPackageName) {
        this.rootPackageName = rootPackageName;
        return this;
    }

    public DefaultExecuteServiceFactory generatorRootPath(String generatorRootPath) {
        this.generatorRootPath = generatorRootPath;
        return this;
    }

    public DefaultExecuteServiceFactory poPackageSuffix(String poPackageSuffix) {
        this.poPackageSuffix = poPackageSuffix;
        return this;
    }

    public DefaultExecuteServiceFactory tablePrefixFilter(String tablePrefixFilter) {
        this.tablePrefixFilter = tablePrefixFilter;
        return this;
    }

}
