package com.example.demo.controller;


import com.example.demo.entity.StringEncode;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dong.youyang
 * @Date: 2019/5/31 19:05
 * @Description: 第一次创建controller
 */
@RestController
public class HelloController {


    @Autowired
    private UserService userService;
    private  int id = 0;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  String getLogin(){

        return  "login";
    }

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public List<User> getUser(){

        List list = userService.getAllUsers();

        return  list;
    }

    @RequestMapping(value = "/getUserInfo/{id}",method = RequestMethod.GET)
    public  String getUserInfo(@PathVariable int id){
        User user = userService.getUser(id);
        System.out.print(user);

        return  user.toString();
    }

    /**
     * 添加一个加密字符串
     */
    @RequestMapping(value = "/addStrings/{context}",method = RequestMethod.GET)
    public  List<StringEncode> addStringEncode(@PathVariable String context){

        userService.addStringEncode(context);

        List<StringEncode> list =  userService.queryStringEncode();

        return list ;
    }

}
