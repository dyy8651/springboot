package com.example.demo.entity;

/**
 * @Author: dong.youyang
 * @Date: 2019/6/1 0:00
 * @Description: user对象
 */
public class StringEncode {

    private Integer id;
    private String password;
    private String ciphertext;

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
