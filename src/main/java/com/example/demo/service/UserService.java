package com.example.demo.service;


import com.example.demo.dao.UserMapper;
import com.example.demo.entity.StringEncode;
import com.example.demo.entity.User;
import com.example.demo.utils.Encryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: dong.youyang
 * @Date: 2019/5/31 19:50
 * @Description: 业务逻辑查询学生信息
 */
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public User getUser(int id) {

        return userMapper.getUserInfo(id);
    }

    public List<User> getAllUsers() {

        return userMapper.getAllUsers();
    }

    /**
     * 添加一个加密字符串
     */
    public void addStringEncode(String context) {

        StringEncode str = new StringEncode();
        str.setPassword(context);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");//加密方式，默认PBEWithMD5AndDES，可改PBEWithMD5AndTripleDES
        config.setPassword("lj&92&jm");//加密所需的salt(盐)
        //应用配置
        encryptor.setConfig(config);
        String ciphertext = Encryptor.encryption(encryptor, context);
        str.setCiphertext(ciphertext);
        userMapper.addStringEncode(str);
    }

    /**
     * 查询加密字符串
     */
    public List<StringEncode> queryStringEncode() {

        return userMapper.queryStringEncode();
    }
}
