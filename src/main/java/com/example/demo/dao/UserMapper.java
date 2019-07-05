package com.example.demo.dao;

import com.example.demo.entity.StringEncode;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
   User getUserInfo(int id);
   List<User> getAllUsers();
   int addStringEncode(StringEncode password);
   List<StringEncode> queryStringEncode();
}
