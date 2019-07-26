package com.zys.my.shop.web.ui.api;

import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.utils.HttpClientUtils;
import com.zys.my.shop.utils.MapperUtils;
import com.zys.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-shop
 * @description: 用户管理api
 * @author: Leo
 * @create: 2019-06-22 21:53
 **/
public class UsersApi {
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[]{}));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }

    public static TbUser register(TbUser tbUser) throws Exception{
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        params.add(new BasicNameValuePair("email",tbUser.getPassword()));
        String json = HttpClientUtils.doPost(API.API_USERS_REGISTER, params.toArray(new BasicNameValuePair[]{}));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}