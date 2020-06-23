package com.aoeai.spin.accelerator.generate.constant;

/**
 * Java类型
 * @author aoe
 */
public enum JavaTypeEnum {
    STRING("String", null),
    INTEGER("Integer", null),
    LONG("Long", null),
    DATE("Date", "java.util.Date"),
    FLOAT("Float", null),
    DOUBLE("Double", null),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    Big_Integer("BigInteger", "java.math.BigInteger"),
    ;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 全称
     */
    private String fullName;

    JavaTypeEnum(String shorthand, String fullName) {
        this.shortName = shorthand;
        this.fullName = fullName;
    }

    public String shortName(){
        return shortName;
    }

    public String fullName(){
        return fullName;
    }
}
