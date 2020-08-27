package com.aoeai.spin.accelerator.themes1.customize.xy.channel.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.aoeai.spin.accelerator.generate.constant.JavaTypeEnum;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.persistent.bean.POField;
import com.aoeai.spin.accelerator.generate1.IPoFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class XyChannelPoFactory implements IPoFactory {

    @Resource
    private IPoFactory poFactory;

    /**
     * 创建PO（数据库对应的）持久对象
     *
     * @param tableName
     * @return
     */
    @Override
    public Po build(String tableName) {
        Po po = poFactory.build(tableName);

        // 用Long替换BigInteger
        Set<String> importList = po.getImportList();
        if (!CollectionUtil.isEmpty(importList)) {
            importList.remove("java.math.BigInteger");
        }
        List<POField> fieldList = po.getFieldList();
        if (!CollectionUtil.isEmpty(fieldList)) {
            for (POField field : fieldList) {
                if ("BigInteger".equals(field.getClassShortName())) {
                    field.setClassShortName(JavaTypeEnum.LONG.shortName());
                    field.setClassFullName(JavaTypeEnum.LONG.fullName());
                }
            }
        }

        return po;
    }
}
