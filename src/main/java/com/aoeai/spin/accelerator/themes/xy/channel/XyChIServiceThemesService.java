package com.aoeai.spin.accelerator.themes.xy.channel;

import com.aoeai.spin.accelerator.generate.common.IBaseRule;
import com.aoeai.spin.accelerator.generate.factory.RuleFactory;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate.persistent.rule.PersistentRule;
import com.aoeai.spin.accelerator.generate.service.bean.ServiceClass;
import com.aoeai.spin.accelerator.generate.service.rule.ServiceRule;
import com.aoeai.spin.accelerator.generate.service.service.ServiceClassService;
import com.aoeai.spin.accelerator.generate.web.rule.WebRule;
import com.aoeai.spin.accelerator.themes.POThemesService;
import com.aoeai.spin.accelerator.themes.ServiceThemesService;
import com.aoeai.spin.accelerator.themes.WebThemesService;
import freemarker.template.TemplateException;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Service接口模板
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class XyChIServiceThemesService implements ServiceThemesService {

    @Resource
    private ServiceClassService serviceClassService;

    @Resource
    private WebThemesService xyChIWebThemesService;

    @Resource
    private POThemesService xyChPOThemesService;

    private String yamlName;

    @PostConstruct
    private void init(){
        yamlName = "/themes/xy/channel/config.yml";
    }

    @Override
    public ServiceClass getServiceClass(String tableName) {
        IBaseRule baseRule = RuleFactory.buildBaseRule(yamlName, tableName);
        PersistentRule persistentRule = RuleFactory.buildPersistentRule(baseRule);
        ServiceRule serviceRule = RuleFactory.buildServiceRule(baseRule);
        WebRule webRule = RuleFactory.buildWebRule(baseRule);
        ServiceClass serviceClass = serviceClassService.buildServiceClass(tableName, baseRule, persistentRule, serviceRule, webRule);
        // 个性化设置
        serviceClass.setPackageName("com.starbuds.server.api.channel.common.service.admin");
        serviceClass.setClassName(serviceClass.getClassName().replaceFirst("Channel", "ChChannel"));

        String fileName = serviceClass.getFile().toString();
        fileName = fileName.replaceFirst("channel/service", "common/service/admin")
                .replaceFirst("Channel", "ChChannel");
        serviceClass.setFile(new File(fileName));

        serviceClass.setPageListQO(xyChIWebThemesService.getPageListQO(tableName));
        serviceClass.setVo(xyChIWebThemesService.getVO(tableName));
        PO po = xyChPOThemesService.getPO(tableName);
        serviceClass.setPo(po);
        String pkColumn = "";
        for (POField field : po.getFieldList()) {
            if (field.getIsPrimaryKey()) {
                pkColumn = field.getName();
                break;
            }
        }
        serviceClass.setPkColumn(pkColumn);
        String pk = WordUtils.capitalize(pkColumn);
        serviceClass.setSetPkMethod("set" + pk);
        serviceClass.setGetPkMethod("get" + pk);

        serviceClass.setMapperClass(xyChPOThemesService.getMapperClass(tableName));
        serviceClass.setMapperClassVariable(WordUtils.uncapitalize(serviceClass.getMapperClass().getClassName()));

        serviceClass.setTemplates("xy/channel/service/i_service.ftl");
        return serviceClass;
    }

    @Override
    public void createServiceClassFile(String tableName) throws IOException, TemplateException {
        serviceClassService.createServiceClassFile(getServiceClass(tableName));
    }
}
