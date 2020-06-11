package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * PO 主题服务
 * @author aoe
 * @date 2020/6/7
 */
public interface POThemesService {

    PO getPO(String tableName);

    /**
     * 创建PO文件
     * @param tableName
     */
    void createPOFile(String tableName) throws IOException, TemplateException;
}
