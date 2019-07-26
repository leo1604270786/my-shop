package com.zys.my.shop.web.admin.web.controller;

import com.zys.my.shop.constant.ConstantUtils;
import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.web.admin.commons.utils.CookieUtils;
import com.zys.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: my-shop
 * @description: 登录控制器
 * @author: Leo
 * @create: 2019-06-01 19:10
 **/
@Controller
public class LoginController{

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response){
        String userInfo = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_NAME_USER_INFO);
        if (StringUtils.isNotBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            request.setAttribute("email",userInfoArray[0]);
            request.setAttribute("password",userInfoArray[1]);
            request.setAttribute("isRemember",true);
        }
        return "login";
    }
    /** 
    * @Description: 登录
    * @Param: [email, password, request,response,model]
    * @return: java.lang.String 
    * @Author: Leo 
    */ 
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password,
                        HttpServletRequest request, HttpServletResponse response, Model model){

        TbUser tbUser = tbUserService.login(email, password);
        //登录失败
        if (tbUser == null){
            model.addAttribute("message","用户名或密码错误！");
            return "login";
        }
        //登录成功
        else{
            //将登录信息保存到会话中
            request.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }
    /** 
    * @Description: 注销 
    * @Param: [request] 
    * @return: java.lang.String 
    * @Author: Leo 
    */ 
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}