package com.aoeai.spin.accelerator.generate1;

import com.aoeai.spin.accelerator.generate.persistent.bean.Po;

/**
 * PO（数据库对应的）持久对象 工厂
 * @author aoe
 */
public interface IPoFactory extends BuildFactory {

    /**
     * 创建PO（数据库对应的）持久对象
     * @param tableName
     * @return
     */
    @Override
    Po build(String tableName);
}
