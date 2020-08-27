package com.aoeai.spin.accelerator.generate1;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate1.bean.config.JavaConfig;
import org.springframework.beans.BeanUtils;

import java.io.File;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class MapperXmlFactory implements IMapperXmlFactory {

    private IMapperFactory mapperFactory;

    private String configYaml;

    public MapperXmlFactory(IMapperFactory mapperFactory, String configYaml) {
        this.mapperFactory = mapperFactory;
        this.configYaml = configYaml;
    }

    @Override
    public MapperXml build(String tableName) {
        MapperXml xml = new MapperXml();
        JavaConfig cfg = ConfigTools.getConfig(configYaml, JavaConfig.class);
        BeanUtils.copyProperties(cfg, xml);
        MapperClass mapperClass = mapperFactory.build(tableName);
        xml.setMapperClass(mapperClass);

        String fileName = StrUtil.format("{}{}.xml", cfg.getFilePath(), mapperClass.getClassName());
        xml.setFile(new File(fileName));

        return xml;
    }
}
