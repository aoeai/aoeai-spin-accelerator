package com.aoeai.spin.accelerator.themes.customize.xy.rtc.config;

import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import lombok.Data;

/**
 * PO（数据库对应的）持久对象 配置信息
 * @author aoe
 * @date 2020/8/24
 */
@Data
public class XyRtcManageFacadeImplConfig extends JavaConfig {

    /**
     * url 请求后缀
     */
    private String actionSuffix;
}
