package com.aoeai.spin.accelerator.generate.test.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.web.bean.Form;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import lombok.Data;

import java.util.List;

/**
 * 控制器测试类
 * @author aoe
 * @date 2020/6/23
 */
@Data
public class ControllerTest extends BaseClassProperty {

    /**
     * 测试主机地址
     */
    private String hostTest;

    /**
     * url 前缀
     */
    private String urlPrefix;

    private Form form;

    private PageListQO pageListQO;

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
