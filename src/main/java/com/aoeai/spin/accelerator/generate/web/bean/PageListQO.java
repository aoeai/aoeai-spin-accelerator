package com.aoeai.spin.accelerator.generate.web.bean;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import lombok.Data;

/**
 * 分页查询对象类
 * @author aoe
 * @date 2020/6/22
 */
@Data
public class PageListQO extends BaseClassProperty {

    private PO po;
}
