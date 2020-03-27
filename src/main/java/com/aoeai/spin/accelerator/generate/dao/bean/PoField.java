package com.aoeai.spin.accelerator.generate.dao.bean;

import lombok.Data;

/**
 * （数据库对应的）实体类字段
 */
@Data
public class PoField {

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型的完整包名
     */
    private String typePackageName;
}
