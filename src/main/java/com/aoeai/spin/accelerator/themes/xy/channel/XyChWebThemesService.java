package com.aoeai.spin.accelerator.themes.xy.channel;

import com.aoeai.spin.accelerator.generate.web.bean.Controller;
import com.aoeai.spin.accelerator.generate.web.bean.Form;
import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import com.aoeai.spin.accelerator.generate.web.service.WebService;
import com.aoeai.spin.accelerator.themes.WebThemesService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 控制器实现类模板
 * @author aoe
 * @date 2020/6/22
 */
@Service
public class XyChWebThemesService implements WebThemesService {

    @Resource
    private WebService webService;

    @Resource
    private WebThemesService xyChIWebThemesService;

    @Override
    public PageListQO getPageListQO(String tableName) {
        return xyChIWebThemesService.getPageListQO(tableName);
    }

    @Override
    public void createPageListQOFile(String tableName) throws IOException, TemplateException {
        xyChIWebThemesService.createPageListQOFile(tableName);
    }

    @Override
    public VO getVO(String tableName) {
        return xyChIWebThemesService.getVO(tableName);
    }

    @Override
    public void createVOFile(String tableName) throws IOException, TemplateException {
        xyChIWebThemesService.createVOFile(tableName);
    }

    @Override
    public Form getForm(String tableName) {
        return xyChIWebThemesService.getForm(tableName);
    }

    @Override
    public void createFormFile(String tableName) throws IOException, TemplateException {
        xyChIWebThemesService.createFormFile(tableName);
    }

    @Override
    public Controller getController(String tableName) {
        Controller controller = xyChIWebThemesService.getController(tableName);
        controller.setClassName(controller.getClassName() + "Impl");
        controller.setInterfaceClass(xyChIWebThemesService.getController(tableName));
        String fileName = controller.getFile().toString().replaceFirst("v1/facade", "v1/impl")
                .replaceFirst(controller.getInterfaceClass().getClassName(), controller.getClassName());
        controller.setFile(new File(fileName));
        controller.setPackageName(controller.getInterfaceClass().getPackageName()
                .replaceFirst("v1.facade", "v1.impl"));
        controller.setTemplates("xy/channel/web/controller.ftl");
        return controller;
    }

    @Override
    public void createControllerFile(String tableName) throws IOException, TemplateException {
        webService.createControllerFile(getController(tableName));
    }
}
