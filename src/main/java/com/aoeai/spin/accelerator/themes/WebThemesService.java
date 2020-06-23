package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.generate.web.bean.PageListQO;
import com.aoeai.spin.accelerator.generate.web.bean.VO;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * web 层主题服务
 */
public interface WebThemesService {

    PageListQO getPageListQO(String tableName);

    /**
     * 创建 QO 文件
     * @param tableName
     */
    void createPageListQOFile(String tableName) throws IOException, TemplateException;

    VO getVO(String tableName);

    /**
     * 创建 VO 文件
     * @param tableName
     */
    void createVOFile(String tableName) throws IOException, TemplateException;
}
