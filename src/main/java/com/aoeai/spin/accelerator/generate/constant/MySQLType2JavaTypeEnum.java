package com.aoeai.spin.accelerator.generate.constant;

import com.aoeai.spin.accelerator.generate.exception.UnkonwMySqlTypeException;

/**
 * MySQL数据库类型转Java类型
 */
public enum MySQLType2JavaTypeEnum {
    CHAR("char", JavaTypeEnum.STRING),
    VARCHAR("varchar", JavaTypeEnum.STRING),
    NVARCHAR("nvarchar", JavaTypeEnum.STRING),
    TEXT("text", JavaTypeEnum.STRING),
    LONGTEXT("longtext", JavaTypeEnum.STRING),

    TINYINT("tinyint", JavaTypeEnum.INTEGER),
    SMALLINT("smallint", JavaTypeEnum.INTEGER),
    INT("int", JavaTypeEnum.INTEGER),
    BIGINT("bigint", JavaTypeEnum.Big_Integer),

    FLOAT("float", JavaTypeEnum.FLOAT),
    DOUBLE("double", JavaTypeEnum.DOUBLE),
    DECIMAL("decimal", JavaTypeEnum.BIG_DECIMAL),

    DATE("date", JavaTypeEnum.DATE),
    DATETIME("datetime", JavaTypeEnum.DATE),
    TIMESTAMP("timestamp", JavaTypeEnum.DATE),
    ;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * Java类型
     */
    private JavaTypeEnum javaType;

    MySQLType2JavaTypeEnum(String dbType, JavaTypeEnum javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    /**
     * 根据MySQL数据类型获得Java类型
     * @param dbType
     * @param maxLength 字段最大长度
     * @return Java类型
     * @throws UnkonwMySqlTypeException
     */
    public static JavaTypeEnum javaType(String dbType, Integer maxLength) throws UnkonwMySqlTypeException {
        for (MySQLType2JavaTypeEnum mysqlType : values()) {
            if (MySQLType2JavaTypeEnum.BIGINT.dbType.equals(dbType)
            && maxLength != null && maxLength < 14) {
                return JavaTypeEnum.LONG;
            }
            if (dbType.startsWith(mysqlType.dbType)) {
                return mysqlType.javaType;
            }
        }

        throw new UnkonwMySqlTypeException(dbType);
    }

    public String dbType(){
        return dbType;
    }
}
