package com.aoeai.spin.accelerator.themes.customize.xy.finance.factory;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.IMapperFactory;
import com.aoeai.spin.accelerator.generate.IMapperXmlFactory;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperClass;
import com.aoeai.spin.accelerator.generate.persistent.bean.MapperXml;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.File;

/**
 * @author aoe
 * @date 2020/8/25
 */
public class XyFinMapperXmlFactory implements IMapperXmlFactory {

    private IMapperFactory mapperFactory;

    public XyFinMapperXmlFactory(IMapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public MapperXml build(String tableName) {
        MapperXml xml = new MapperXml();
        JavaConfig cfg = ConfigTools.getConfig("/themes/xy/finance/config/mapper-xml.yaml", JavaConfig.class);
        BeanUtils.copyProperties(cfg, xml);
        MapperClass mapperClass = mapperFactory.build(tableName);
        xml.setMapperClass(mapperClass);

        String fileName = StrUtil.format("{}{}.xml", cfg.getFilePath(), mapperClass.getClassName());
        fileName = StringUtils.replace(fileName, "DAO.xml", "Mapper.xml");
        xml.setFile(new File(fileName));

        return xml;
    }
}
