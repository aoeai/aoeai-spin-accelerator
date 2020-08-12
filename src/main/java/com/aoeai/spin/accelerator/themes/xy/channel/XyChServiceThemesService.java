package com.aoeai.spin.accelerator.themes.xy.channel;

import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.service.ServiceClassService;
import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
import freemarker.template.TemplateException;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Service实现类模板
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class XyChServiceThemesService implements ServiceThemesService {

    @Resource
    private ServiceClassService serviceClassService;

    @Resource
    private XyChIServiceThemesService xyChIServiceThemesService;

    @Resource
    private POThemesService xyChPOThemesService;

    @Override
    public ServiceClass getServiceClass(String tableName) {
        ServiceClass serviceClass = xyChIServiceThemesService.getServiceClass(tableName);
        serviceClass.setInterfaceClass(xyChIServiceThemesService.getServiceClass(tableName));
        // 个性化设置
        serviceClass.setPackageName("com.starbuds.server.api.channel.service.admin.impl");
        serviceClass.setClassName(serviceClass.getClassName() + "Impl");
//
        String fileName = serviceClass.getFile().toString();
        fileName = fileName.replaceFirst("common/service/admin", "service/admin/impl")
                .replaceFirst(serviceClass.getInterfaceClass().getClassName(), serviceClass.getClassName());
        serviceClass.setFile(new File(fileName));

        serviceClass.setMapperClass(xyChPOThemesService.getMapperClass(tableName));
        serviceClass.setMapperClassVariable(WordUtils.uncapitalize(serviceClass.getMapperClass().getClassName()));

        serviceClass.setTemplates("xy/channel/service/service.ftl");
        return serviceClass;
    }

    @Override
    public void createServiceClassFile(String tableName) throws IOException, TemplateException {
        serviceClassService.createServiceClassFile(getServiceClass(tableName));
    }
}
