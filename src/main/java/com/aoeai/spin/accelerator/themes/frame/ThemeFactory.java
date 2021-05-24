package com.aoeai.spin.accelerator.themes.frame;

import com.aoeai.spin.accelerator.generate.common.IGenerateProperty;
import com.aoeai.spin.accelerator.themes.frame.bean.Module;
import com.aoeai.spin.accelerator.themes.frame.bean.ThemeType;
import com.aoeai.spin.accelerator.themes.frame.exception.ThemeException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 主题工厂
 *
 * @author aoe
 * @date 2020/8/24
 */
@Component
public class ThemeFactory {

    /**
     * 模板Map
     * key:模板代号; value 模板类型
     */
    private Map<String, ThemeType> themeTypeMap;

    @PostConstruct
    private void init() {
        themeTypeMap = new TreeMap<>();
    }

    /**
     * 注册模板类型
     * @param themeType
     */
    public void registerThemeType(ThemeType themeType) {
        String themeCode = themeType.getCode();
        if (themeTypeMap.get(themeCode) != null) {
            throw new ThemeException("出现重复的模板代号 " + themeCode);
        }
        themeTypeMap.put(themeCode, themeType);
    }

    /**
     * 建造模块对象
     * @param themeCode
     * @param moduleCode
     * @param <T>
     * @return
     */
    public <T extends IGenerateProperty>T buildModule(String themeCode, String moduleCode, String tableName){
        for (Module module : getModules(themeCode)) {
            if (moduleCode.equals(module.getCode())) {
                return module.getFactory().build(tableName);
            }
        }
        return null;
    }

    /**
     * 获得模块列表
     * @param themeCode
     * @return
     */
    public List<Module> getModules(String themeCode) {
        var themeType = themeTypeMap.get(themeCode);
        return themeType.getModules();
    }

    /**
     * 获得所有模板列表
     * @return
     */
    public List<ThemeType> getThemeTypes(){
        List<ThemeType> list = new ArrayList<>(themeTypeMap.size());
        for (Map.Entry<String, ThemeType> entry : themeTypeMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * 获得模板
     * @param code 模板代码
     * @return
     */
    public ThemeType getThemeType(String code) {
        return themeTypeMap.get(code);
    }
}
