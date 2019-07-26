package com.zys.my.shop.web.ui.api;

/**
 * @program: my-shop
 * @description: api接口常量
 * @author: Leo
 * @create: 2019-06-14 22:20
 **/
public class API {
    //主机地址
    public static final String HOST = "http://localhost:8081/api/v1";
    //内容api接口地址
    public static final String API_CONTENTS = HOST + "/contents/";
    //会员登录api接口地址
    public static final String API_USERS_LOGIN = HOST + "/users/login";
    //会员注册api接口地址
    public static final String API_USERS_REGISTER = HOST + "/users/register";
}