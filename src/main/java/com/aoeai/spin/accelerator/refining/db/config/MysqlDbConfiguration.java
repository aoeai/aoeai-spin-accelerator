package com.aoeai.spin.accelerator.refining.db.config;

/**
 * MySQL数据库配置
 */
public class MysqlDbConfiguration implements IDbConfiguration {

    private String host;

    private String port;

    private String user;

    private String password;

    private String database;

    public MysqlDbConfiguration(String host, String port, String user, String password, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    @Override
    public String host() {
        return host;
    }

    @Override
    public String port() {
        return port;
    }

    @Override
    public String user() {
        return user;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String database() {
        return database;
    }
}
