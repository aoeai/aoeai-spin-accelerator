package com.aoeai.spin.accelerator.generate.execute;

import com.aoeai.spin.accelerator.generate.constant.BuildPOServiceEnum;
import com.aoeai.spin.accelerator.generate.dao.bean.PO;
import com.aoeai.spin.accelerator.generate.dao.bean.POField;
import com.aoeai.spin.accelerator.generate.factory.DefaultExecuteServiceFactory;
import com.aoeai.spin.accelerator.generate.factory.ExecuteServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 创建持久对象-默认实现 测试类
 */
public class DefaultBuildPOServiceImplTest {

    @Test
    public void test(){
        String rootPath = this.getClass().getResource("/").getPath();
        DefaultExecuteServiceFactory defaultExecuteServiceFactory
                = new DefaultExecuteServiceFactory(BuildPOServiceEnum.DEFAULT)
                // IDbConfiguration 数据库配置信息
                .host("localhost").port("3306")
                .user("root").password("root")
                .database("spin-accelerator-test")
                // IBaseRule （生成时的）基础规则
                .rootPackageName("com.aoeai.test") // 工程根路径的包名
                .generatorRootPath(rootPath) // 生成文件的主文件夹路径 为空时，默认为当前工程路径下的target/build/ 必须有结束的"/"
                // IDaoRule （生成时的）Dao规则
                .poPackageSuffix("po") // PO(持久对象)类所在位置的包名后缀
                .tablePrefixFilter(""); // 生成Java文件时需要过滤掉的表名前缀（,分割）
        ExecuteServiceFactory executeServiceFactory = defaultExecuteServiceFactory;
        BuildPOService buildPOService = executeServiceFactory.buildPOService();

        Map<String, PO> poMap = buildPOService.allPOMap();
        PO po = poMap.get("satest_problem");
        Assert.assertEquals(po.getClassName(), "SatestProblem");
        Assert.assertEquals(po.getPackageName(), "com.aoeai.test.po");
        Assert.assertEquals(po.getClassComment(), "LeetCode问题");

        // 验证 import 列表
        Set<String> importSet = new HashSet<>();
        importSet.add("java.util.Date");
        importSet.add("java.math.BigDecimal");
        importSet.add("java.math.BigInteger");
        for (String importStr : po.getImportList()) {
            importSet.remove(importStr);
        }
        Assert.assertEquals(importSet.isEmpty(), true);

        // 验证（数据库对应的）实体类字段
        Set<String> poFieldSet = new HashSet();
        // 验证类型转换
        poFieldSet.add(getPoFieldSetKey("charTest","String","null", ""));
        poFieldSet.add(getPoFieldSetKey("varcharTest","String","null", ""));
        poFieldSet.add(getPoFieldSetKey("nvarcharTest","String","null", ""));
        poFieldSet.add(getPoFieldSetKey("textTest","String","null", ""));
        poFieldSet.add(getPoFieldSetKey("longtextTest","String","null", ""));

        poFieldSet.add(getPoFieldSetKey("tinyintTest","Integer","null", ""));
        poFieldSet.add(getPoFieldSetKey("smallintTest","Integer","null", ""));
        poFieldSet.add(getPoFieldSetKey("intTest","Integer","null", ""));
        poFieldSet.add(getPoFieldSetKey("bigintTest","BigInteger","java.math.BigInteger", ""));

        poFieldSet.add(getPoFieldSetKey("floatTest","Float","null", ""));
        poFieldSet.add(getPoFieldSetKey("decimalTest","BigDecimal","java.math.BigDecimal", ""));

        poFieldSet.add(getPoFieldSetKey("dateTest","Date","java.util.Date", ""));
        poFieldSet.add(getPoFieldSetKey("datetimeTest","Date","java.util.Date", ""));
        poFieldSet.add(getPoFieldSetKey("timestampTest","Date","java.util.Date", ""));

        // 驼峰命名测试、字段注释测试
        poFieldSet.add(getPoFieldSetKey("humpNameTest","String","null", "驼峰命名测试"));

        for (POField poField : po.getFieldList()) {
            String key = getPoFieldSetKey(poField.getName(), poField.getClassShortName(), poField.getClassFullName(), poField.getComment());
            poFieldSet.remove(key);
        }
        Assert.assertEquals(poFieldSet.isEmpty(), true);
    }

    /**
     *
     * @param name 字段名称
     * @param classShortName 字段类型：类名简称（无包名）
     * @param classFullName 字段类型：类名全称（有包名）
     * @param comment 注释
     * @return
     */
    private String getPoFieldSetKey(String name, String classShortName, String classFullName, String comment){
        comment = StringUtils.isBlank(comment) ? "" :  comment;
        String split = "-";
        return name + split + classShortName + split + classFullName + split + comment;
    }
}
