package com.aoeai.spin.accelerator.generate.dao.bean;

import lombok.Data;

import java.util.List;

/**
 * （数据库对应的）持久对象
 */
@Data
public class Po {

    /**
     * 包名
     */
    private String packageName;

    /**
     * import语句
     */
    private List<String> importList;

    /**
     * 类名
     */
    private String className;

    /**
     * 类注释
     */
    private String classComment;

    /**
     * 字段列表
     */
    private List<PoField> fieldList;
}
