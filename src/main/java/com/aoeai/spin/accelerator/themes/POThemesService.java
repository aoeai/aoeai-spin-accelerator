package com.aoeai.spin.accelerator.themes;

import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.persistent.bean.PO;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperService;
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

    /**
     * 获得Mybatis Mapper 文件对象
     * @param tableName
     * @return
     */
    MapperClass getMapperClass(String tableName);

    /**
     * 创建Mybatis Mapper 文件
     * @param tableName
     */
    void createMapperClassFile(String tableName) throws IOException, TemplateException;

    /**
     * 获得 Mybatis XML 对象
     * @param tableName
     * @return
     */
    MapperXml getMapperXml(String tableName);

    /**
     * 创建 Mybatis XML 文件
     * @param tableName
     */
    void createMapperXmlFile(String tableName) throws IOException, TemplateException;

    /**
     * 获得 Mybatis Plus Service 对象
     * @param tableName
     * @return
     */
    MapperService getMapperService(String tableName);

    /**
     * 创建 Mybatis Plus Service 文件
     * @param tableName
     */
    void createMapperServiceFile(String tableName) throws IOException, TemplateException;
}
