package com.example.demo.service;


import com.example.demo.dao.UserMapper;
import com.example.demo.entity.StringEncode;
import com.example.demo.entity.User;
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

        userMapper.addStringEncode(str);
    }

    /**
     * 查询加密字符串
     */
    public List<StringEncode> queryStringEncode() {

        return userMapper.queryStringEncode();
    }
}
