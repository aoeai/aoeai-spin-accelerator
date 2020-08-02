package com.aoeai.spin.accelerator.admin.controller;

import com.aoeai.spin.accelerator.refining.db.service.DBService;
import com.aoeai.spin.accelerator.themes.constant.ThemeTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aoe
 * @date 2020/6/5
 */
@Controller
public class IndexController {

    @Resource
    private DBService dbService;

    /**
     * 首页
     * @param tableName
     * @param model
     * @return 数据库列表
     */
    @GetMapping("/")
    public String index(@RequestParam(required = false) String tableName, @RequestParam(required = false) String theme, Model model){
        List tableList =  dbService.getTableList(tableName);
        model.addAttribute("tableList", tableList);
        model.addAttribute("themeList", ThemeTypeEnum.values());

        // 搜索条件
        model.addAttribute("tableName", tableName);
        model.addAttribute("themeHis", theme);
        return "web/index";
    }

    @GetMapping("/detail")
    public String detail(String tableName, String theme, Model model) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("theme", ThemeTypeEnum.toEnum(theme));
        return "web/table-detail";
    }
}
