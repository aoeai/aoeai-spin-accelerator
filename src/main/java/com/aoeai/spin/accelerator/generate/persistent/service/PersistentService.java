package com.aoeai.spin.accelerator.generate.persistent.service;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.rule.IPersistentRule;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 持久层服务
 */
public interface PersistentService {

    /**
     * 建造（数据库对应的）持久对象
     * @param tableName
     * @param baseRule
     * @param persistentRule
     * @return
     */
    PO buildPO(String tableName, IBaseRule baseRule, IPersistentRule persistentRule);

    /**
     * 创建PO文件
     * @param po
     */
    void createPOFile(PO po) throws IOException, TemplateException;
}
