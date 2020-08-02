package com.aoeai.spin.accelerator.themes.constant;

/**
 * 模板类型
 */
public enum ThemeTypeEnum {

    XY_WA_ZHI_MA("xy-wazhima", "星芽-挖芝麻"),
    XY_CHANNEL("xy-channel", "星芽-渠道"),
    ;

    private String theme;

    private String themeName;

    ThemeTypeEnum(String theme, String name) {
        this.theme = theme;
        this.themeName = name;
    }

    public static ThemeTypeEnum toEnum(String theme) {
        if (theme == null) {
            return null;
        }
        for (ThemeTypeEnum value : values()) {
            if (theme.equals(value.theme)) {
                return value;
            }
        }
        return null;
    }

    public String getTheme() {
        return theme;
    }

    public String getThemeName() {
        return themeName;
    }
}
