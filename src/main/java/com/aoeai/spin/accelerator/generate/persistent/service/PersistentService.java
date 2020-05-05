package com.aoeai.spin.accelerator.generate.persistent.service;

import com.aoeai.spin.accelerator.generate.persistent.bean.PO;

import java.util.Map;

/**
 * 持久层服务
 */
public interface PersistentService {

    /**
     * 所有PO对象
     * @return 所有PO信息的Map key: 表名；value: PO对象的数据
     */
    Map<String, PO> allPOMap();

    /**
     * 生成持久层的Java Bean
     * @param templatePath 模板路径
     */
    void generatePOJavaBean(String templatePath);

    /**
     * 生成MyBatis中Mapper对应的XML文件
     * @param templatePath 模板路径
     */
    void generateMapperXml(String templatePath);
}
