package com.aoeai.spin.accelerator.generate.exception;

/**
 * 未知的MySQL数据类型异常
 */
public class UnkonwMySqlTypeException extends Exception {

    public UnkonwMySqlTypeException() {
        super("未知的MySQL数据类型，请在MySQLType2JavaTypeEnum类中添加映射关系");
    }
}
