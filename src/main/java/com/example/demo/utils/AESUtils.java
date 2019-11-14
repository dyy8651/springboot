package com.example.demo.utils;

/**
 * @Author: dong.youyang
 * @Date: 2019/11/14 21:30
 * @Description:
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


public class AESUtils {

    /**
     * AES加密
     * @param content
     * @return
     */
    public static String encrypt(String content,String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");            // 创建AES的Key生产者
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);


            SecretKey secretKey = kgen.generateKey();                       // 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();                   // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");     // 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");                      // 创建密码器
            byte[] byteContent = content.getBytes("utf-8");                 // 设置转换格式
            cipher.init(Cipher.ENCRYPT_MODE, key);                          // 初始化为加密模式的密码器
            byte[] encrypt = cipher.doFinal(byteContent);                   // 加密
            String result = parseByte2HexStr(encrypt);                      // 防止乱码，转换进制

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * @param content
     * @return
     */
    public static String decrypt(String content,String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");            // 创建AES的Key生产者// 利用用户密码作为随机数初始化出(密码一样，就可以解密)

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);

            SecretKey secretKey = kgen.generateKey();                       // 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();                   // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");     // 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");                      // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);                          // 初始化为解密模式的密码器
            byte[] results = cipher.doFinal(parseHexStr2Byte(content));     // 解密
            return new String(results);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转换十六进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 十六进制转换二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
