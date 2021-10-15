package com.aoeai.spin.accelerator.generate.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author aoe
 * @date 2021/9/16
 */
public class Md5Test {

    public static void main(String[] args) {
        String msg = "";
        for (int i = 0; i < 10000000; i++) {
            msg = "" + i;
            DigestUtils.md5Digest(msg.getBytes(StandardCharsets.UTF_8));
        }
        System.out.println("msg = " + msg);
    }
}
