package com.aoeai.spin.accelerator.generate.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ClassToolsTest {

    @Test
    public void getModelNameTest(){
        String table = "tb_user_car";
        String model = ClassTools.getModelName(table, "tb");
        Assert.assertEquals(model, "user");

        table = "tb_order";
        model = ClassTools.getModelName(table, "tb");
        Assert.assertEquals(model, "order");
    }

}