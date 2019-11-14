package com.example.demo.service;

import com.example.demo.utils.AESUtils;
import com.example.demo.utils.RSAUtils;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

/**
 * @Author: dong.youyang
 * @Date: 2019/11/14 21:33
 * @Description: 加密后台
 */
@Service
public class EncryptService {


    /**
     * 查询加密字符串
     */
    public String useEncrypt(String byte2Base64 ,String encrypt,String privateKeyStr ) {


        String  privatePassword = "";
        try{
            //将Base64编码后的私钥转换成PrivateKey对象
            PrivateKey privateKey = RSAUtils.string2PrivateKey(privateKeyStr);
            //加密后的内容Base64解码
            byte[] base642Byte = RSAUtils.base642Byte(byte2Base64);
            //用私钥解密
            byte[] privateDecrypt = RSAUtils.privateDecrypt(base642Byte, privateKey);
            privatePassword = new String(privateDecrypt);
            //解密后的明文
            System.out.println("解密后aes密码的明文: " + privatePassword);

        }catch (Exception e) {
            e.printStackTrace();
        }

        //业务处理
        if(!"".equals(privatePassword)){

            String password = "2019";
            String resPassword = AESUtils.encrypt(privatePassword,password);

            return resPassword;
        }

        return "失败！";
    }

}
