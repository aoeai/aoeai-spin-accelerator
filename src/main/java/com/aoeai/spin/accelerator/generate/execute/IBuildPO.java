package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.dao.IDaoRule;
import com.aoeai.spin.accelerator.generate.dao.bean.PO;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfiguration;

import java.util.Map;

/**
 * 创建持久对象
 */
public interface IBuildPO {

    /**
     * 创建PO对象
     * @param dbConf 数据库配置信息
     * @param baseRule （生成时的）基础规则
     * @param daoRule （生成时的）Dao规则
     * @return 所有PO信息的Map key: 表名；value: PO对象的数据
     */
    Map<String, PO> allPOMap(IDbConfiguration dbConf, IBaseRule baseRule, IDaoRule daoRule);
}
