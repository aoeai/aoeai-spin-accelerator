package com.aoeai.spin.accelerator.themes.customize.xy.dashang.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IMapperXmlFactory;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import org.springframework.beans.BeanUtils;

import java.io.File;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class XyDsMapperXmlFactory implements IMapperXmlFactory {

    private IMapperFactory mapperFactory;

    public XyDsMapperXmlFactory(IMapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public MapperXml build(String tableName) {
        var xml = new MapperXml();
        JavaConfig cfg = ConfigTools.getConfig("/themes/xy/dashang/config/mapper-xml.yaml", JavaConfig.class);
        BeanUtils.copyProperties(cfg, xml);
        var mapperClass = mapperFactory.build(tableName);
        xml.setMapperClass(mapperClass);

        var fileName = StrUtil.format("{}{}.xml", cfg.getFilePath(), mapperClass.getClassName());
        fileName = fileName.replaceFirst("DAO", "Mapper");
        xml.setFile(new File(fileName));

        return xml;
    }
}
