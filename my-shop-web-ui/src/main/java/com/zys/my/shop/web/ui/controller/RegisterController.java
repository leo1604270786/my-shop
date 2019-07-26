package com.zys.my.shop.web.ui.controller;

import com.zys.my.shop.web.ui.dto.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: my-shop
 * @description: 注册控制器
 * @author: Leo
 * @create: 2019-06-22 22:04
 **/
@Controller
public class RegisterController {
    /**
    * @Description: 跳转到注册页
    * @return: java.lang.String
    * @Author: Leo
    */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    /**
    * @Description: 注册
    * @Param: [tbUser,model]
    * @return: java.lang.String
    * @Author: Leo
    */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(TbUser tbUser, Model model){

        return "register";
    }
}