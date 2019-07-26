package com.zys.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: my-shop
 * @description: 用户数据传输对象
 * @author: Leo
 * @create: 2019-06-22 21:54
 **/
@Data
public class TbUser implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;
}