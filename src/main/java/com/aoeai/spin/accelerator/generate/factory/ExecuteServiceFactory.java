package com.aoeai.spin.accelerator.generate.factory;

import com.aoeai.spin.accelerator.generate.execute.BuildPOService;

/**
 * 执行生成的服务类工厂接口
 */
public interface ExecuteServiceFactory {

    /**
     * 创建持久对象服务实例
     * @return 创建持久对象服务实例
     */
    BuildPOService buildPOService();
}
