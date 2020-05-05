package com.aoeai.spin.accelerator.refining.db.config;

/**
 * 数据库配置信息
 */
public interface IDbConfig {

    /**
     * @return 主机地址
     */
    String host();

    /**
     * @return 端口
     */
    String port();

    /**
     * @return 用户名
     */
    String user();

    /**
     * @return 密码
     */
    String password();

    /**
     * @return 数据库名
     */
    String database();
}
