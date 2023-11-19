package com.aoeai.spin.accelerator.themes.customize.qm.kuyin.bean;

import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.themes.customize.qm.kuyin.factory.QmKuyinManagePageParamFactory;
import lombok.Data;

@Data
public class QmKuyinMapperClass extends MapperClass {

    private QmKuyinManagePageParamFactory managePageParamFactory;

    private String selectPageListParamName;
}