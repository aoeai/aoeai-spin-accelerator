package com.aoeai.spin.accelerator.themes1.customize.xy.channel.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate1.IMapperFactory;
import com.aoeai.spin.accelerator.generate1.IMapperXmlFactory;
import com.aoeai.spin.accelerator.generate1.bean.config.JavaConfig;
import org.springframework.beans.BeanUtils;

import java.io.File;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class XyChMapperXmlFactory implements IMapperXmlFactory {

    private IMapperFactory mapperFactory;

    public XyChMapperXmlFactory(IMapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public MapperXml build(String tableName) {
        MapperXml xml = new MapperXml();
        JavaConfig cfg = ConfigTools.getConfig("/themes/xy/channel1/config/mapper-xml.yaml", JavaConfig.class);
        BeanUtils.copyProperties(cfg, xml);
        MapperClass mapperClass = mapperFactory.build(tableName);
        xml.setMapperClass(mapperClass);

        String fileName = StrUtil.format("{}{}.xml", cfg.getFilePath(), mapperClass.getClassName());
        xml.setFile(new File(fileName));

        return xml;
    }
}
