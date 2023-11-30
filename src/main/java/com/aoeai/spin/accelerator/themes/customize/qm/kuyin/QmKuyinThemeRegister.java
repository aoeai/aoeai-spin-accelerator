package com.aoeai.spin.accelerator.themes.customize.qm.kuyin;

import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IPoFactory;
import com.aoeai.spin.accelerator.themes.customize.qm.kuyin.factory.*;
import com.aoeai.spin.accelerator.themes.customize.xy.rtc.factory.XyRtcMapperFactory;
import com.aoeai.spin.accelerator.themes.frame.AbstractThemeRegister;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class QmKuyinThemeRegister extends AbstractThemeRegister {

    @Resource(name = "qmKunyinPoFactory")
    private IPoFactory poFactory;

    @Resource
    private QmKunyinSaveRpcParamFactory saveRpcParamFactory;
    @Resource
    private QmKunyinUpdateRpcParamFactory updateRpcParamFactory;
    @Resource
    private QmKunyinPageRpcDTOFactory pageRpcDTOFactory;

    @Override
    protected ThemeType getThemeType() {
        ThemeType themeType = new ThemeType();
        themeType.setCode("qm-kuyin");
        themeType.setName("青陌-酷音");

        QmKuyinManagePageParamFactory managePageParamFactory = new QmKuyinManagePageParamFactory(poFactory);
        IMapperFactory manageQmKuyinMapperFactory = new QmKuyinMapperFactory(poFactory);

        List<Module> modules = Arrays.asList(
                new Module("DO", "持久化对象", poFactory)
                , new Module("ManagePageParam", "Manage 层分页查询参数", managePageParamFactory)
                , new Module("Mapper", "Mapper", manageQmKuyinMapperFactory)
                , new Module("SaveRpcParam", "SaveRpcParam", saveRpcParamFactory)
                , new Module("UpdateRpcParam", "UpdateRpcParam", updateRpcParamFactory)
                , new Module("PageRpcDTO", "PageRpcDTO", pageRpcDTOFactory)
        );

        themeType.setModules(modules);
        return themeType;
    }
}
