package com.aoeai.spin.accelerator.refining.db.config;

/**
 * 数据库配置信息
 */
public interface DbConfiguration {

    String host();

    String port();

    String user();

    String password();

    String database();
}
