package com.zys.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: my-shop
 * @description: a
 * @author: Leo
 * @create: 2019-06-02 15:13
 **/
@Controller
public class MainController {
    /**
     * @Description: 跳转到首页
     * @return: java.lang.String 逻辑视图名
     * @Author: Leo
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}