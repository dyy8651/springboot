package com.example.demo.controller;



import com.example.demo.service.EncryptService;
import com.example.demo.utils.AESUtils;
import com.example.demo.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.KeyPair;
import java.security.PublicKey;


/**
 * @Author: dong.youyang
 * @Date: 2019/5/31 19:05
 * @Description: 第一次创建controller
 */
@Controller
public class EncryptController {


    @Autowired
    private EncryptService encryptService;

    /**
     * 加密
     */
    @RequestMapping(value = "/encrypt/{context}",method = RequestMethod.GET)
    public  String encryptStringEncode(@PathVariable String context){

        //产生AES密码
        String aesString = getRandomString(6);
        //明文
        String obvious  = context;
        String password = "2019";
        System.out.println(obvious);
        //加密明文内容
        String encrypt = AESUtils.encrypt(obvious,aesString);
        System.out.println("明文加密后："+encrypt);
        String resPassword ="";
        //RSA对AES密码用公钥加密
        try {
            KeyPair keyPair = RSAUtils.getKeyPair();
            String publicKeyStr = RSAUtils.getPublicKey(keyPair);
            String privateKeyStr = RSAUtils.getPrivateKey(keyPair);
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = RSAUtils.string2PublicKey(publicKeyStr);
            //用公钥加密
            byte[] publicEncrypt = RSAUtils.publicEncrypt(aesString.getBytes(), publicKey);
            //加密后的内容Base64编码
            String byte2Base64 = RSAUtils.byte2Base64(publicEncrypt);
            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

            //响应明文
            resPassword = encryptService.useEncrypt(byte2Base64,encrypt,privateKeyStr);

        }catch (Exception e) {
            e.printStackTrace();
        }

        String decrypt = AESUtils.decrypt(resPassword,password);
        System.out.println("AES密码解密后："+decrypt);

        return "成功" ;
    }

    /**
     * 生成随机字符串
     *
     * @param stringLength:生成的字符串长度
     * @return
     */
    public static String getRandomString(int stringLength) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}
