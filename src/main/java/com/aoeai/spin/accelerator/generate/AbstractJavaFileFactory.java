package com.aoeai.spin.accelerator.generate;

import cn.hutool.core.util.StrUtil;
import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.persistent.bean.Po;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate.bean.config.JavaConfig;
import org.springframework.beans.BeanUtils;

import java.io.File;

/**
 * Java文件抽象工厂
 * @author aoe
 * @date 2020/8/26
 */
public abstract class AbstractJavaFileFactory<T extends BaseClassProperty> implements BuildFactory {

    /**
     * 建造者实例
     */
    protected T builder;

    /**
     * 通用创建逻辑
     * @return
     */
    protected  T create(String tableName, T builder) {
        this.builder = builder;
        JavaConfig cfg = ConfigTools.getConfig(configYaml(), getConfigType());
        BeanUtils.copyProperties(cfg, builder);
        Po po = poFactory().build(tableName);
        builder.setClassComment(po.getTable().getComment());
        builder.setClassName(po.getClassNameWithoutSuffix() + cfg.getSuffix());

        manualCreate(tableName);

        String fileName = StrUtil.format("{}{}.java",
                cfg.getFilePath(), builder.getClassName());
        builder.setFile(new File(fileName));
        doEnd(tableName);
        return builder;
    }

    /**
     * 最后执行的方法
     * @param tableName
     */
    protected void doEnd(String tableName){}

    /**
     * 获得配置信息的类型
     * @return
     */
    protected Class<JavaConfig> getConfigType(){
        return JavaConfig.class;
    }

    /**
     * 存放配置信息的yaml文件
     * @return
     */
    protected abstract String configYaml();

    /**
     * Po工厂
     * @return
     */
    protected abstract IPoFactory poFactory();

    /**
     * 手动创建逻辑
     * @param tableName
     */
    protected abstract void manualCreate(String tableName);
}
