package com.aoeai.spin.accelerator.themes1.customize.xy.wazhima.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import lombok.Data;

import java.util.List;

/**
 * 控制器测试类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class XyWzmControllerTest extends BaseClassProperty {

    /**
     * 测试主机地址
     */
    private String hostTest;

    /**
     * url 前缀
     */
    private String urlPrefix;

    /**
     * url公共路径
     */
    private String urlCommonPath;

    private XyWzmForm form;

    private XyWzmPageListQo pageListQO;

    /**
     * 新增方法参数
     */
    private List<String> createParamList;

    /**
     * 新增/更新方法参数
     */
    private List<String> createOrUpdateParamList;

    /**
     * 更新方法参数
     */
    private List<String> updateParamList;

}
