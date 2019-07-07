package com.example.demo.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @Author: dong.youyang
 * @Date: 2019/7/7 23:49
 * @Description: 加密
 */
public class Encryptor {
    /**
     * Jasypt加密 结果
     * @param encryptor 加密工具
     * @param plaintext 需要加密字符串
     */
    public static String  encryption(StandardPBEStringEncryptor encryptor, String plaintext) {

        //加密
        String ciphertext = encryptor.encrypt(plaintext);
        System.out.println(plaintext + " : " + ciphertext);
        return  ciphertext;
    }
}
