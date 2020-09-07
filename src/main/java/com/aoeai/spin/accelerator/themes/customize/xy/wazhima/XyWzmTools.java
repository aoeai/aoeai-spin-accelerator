package com.aoeai.spin.accelerator.themes.customize.xy.wazhima;

import com.aoeai.spin.accelerator.generate.common.BaseClassProperty;
import com.aoeai.spin.accelerator.generate.utils.ClassTools;
import com.aoeai.spin.accelerator.generate.utils.ConfigTools;
import com.aoeai.spin.accelerator.generate.bean.config.PoConfig;

import java.io.File;

/**
 * @author aoe
 * @date 2020/9/1
 */
public class XyWzmTools {

    /**
     * 模块路径符号
     */
    private final static String SYMBOL_MODEL_PATH = "\\$MODEL_PATH";

    /**
     * 根据表名获得模块名称
     * @param tableName
     * @return
     */
    public static String getModelName(String tableName) {
        PoConfig cfg = ConfigTools.getConfig("/themes/xy/wazhima/config/po.yaml", PoConfig.class);
        return ClassTools.getModelName(tableName, cfg.getTablePrefixFilter());
    }

    /**
     * 替换所有的模块路径符号为模块名称
     * @param val
     * @param tableName
     * @return
     */
    public static String replaceAllModelName(String val, String tableName) {
        String modelName = getModelName(tableName);
        return val.replaceAll(SYMBOL_MODEL_PATH, modelName);
    }

    /**
     * 替换所有的模块路径符号为模块名称
     * @param obj
     * @param tableName
     * @param <T>
     * @return
     */
    public static <T extends BaseClassProperty> T replaceModelName(T obj, String tableName) {
        obj.setPackageName(replaceAllModelName(obj.getPackageName(), tableName));
        File file = obj.getFile();
        obj.setFile(new File(replaceAllModelName(file.toString(), tableName)));
        return obj;
    }
}
