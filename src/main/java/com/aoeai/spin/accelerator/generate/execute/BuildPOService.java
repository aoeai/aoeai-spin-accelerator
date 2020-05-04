package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.dao.bean.PO;

import java.util.Map;

/**
 * 创建持久对象服务接口
 */
public interface BuildPOService {

    /**
     * 创建PO对象
     * @return 所有PO信息的Map key: 表名；value: PO对象的数据
     */
    Map<String, PO> allPOMap();
}
