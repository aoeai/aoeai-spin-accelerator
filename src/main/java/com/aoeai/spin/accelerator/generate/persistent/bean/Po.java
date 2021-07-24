package com.aoeai.spin.accelerator.generate.persistent.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import lombok.Data;

import java.util.List;

/**
 * （数据库对应的）持久对象
 * @author aoe
 */
@Data
public class Po extends BaseClassProperty {

    /**
     * 字段列表
     */
    private List<POField> fieldList;

    // 自定义规则时用
    /**
     * （数据库的）表
     */
    private Table table;

    /**
     * 没有后缀的类名
     */
    private String classNameWithoutSuffix;

    /**
     * 生成Java文件时需要过滤掉的表名前缀（,分割）；不填写默认为空
     */
    private String tablePrefixFilter = "";

    /**
     * 第一个出现的主键（变量名）
     */
    private String firstPrimaryKey;

    /**
     * 第一个出现的主键（列名）
     */
    private String firstPrimaryKeyColumn;
}
