package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author: dong.youyang
 * @Date: 2019/6/1 18:09
 * @Description: 读取配置文件的内容
 */
public class LoadProperties {

    public static void main(String[] args) {

        //读取配置文件中的值
        ResourceBundle resource = ResourceBundle.getBundle("application");
        String str = resource.getString("test");
        String[] strings  = str.split(",");

        for(int i =1;i<=strings.length;i++){

           Map map =  new HashMap<>();
            map.put("stringParams_"+i,resource.getString("stringParams_"+i));
            map.put("intParams_"+i,resource.getString("intParams_"+i));
            System.out.println(map);
        }
    }
}
