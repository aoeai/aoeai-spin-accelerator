package com.aoeai.spin.accelerator.generate.utils;

/**
 * @author aoe
 * @date 2021/9/16
 */
public class NoMd5Test {

    public static void main(String[] args) {
        String msg = "";
        for (int i = 0; i < 10000000; i++) {
            msg = "" + i;
        }
        System.out.println("msg = " + msg);
    }
}
