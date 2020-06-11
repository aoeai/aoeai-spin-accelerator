package com.aoeai.spin.accelerator.generate.persistent.bean;

import com.aoeai.spin.accelerator.refining.db.bean.Table;
import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * （数据库对应的）持久对象
 */
@Data
public class PO {

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
    private List<POField> fieldList;

    /**
     * Java文件
     */
    private File file;

    /**
     * 模板文件路径
     */
    private String templates;

    // 自定义规则时用
    /**
     * （数据库的）表
     */
    private Table table;
}
