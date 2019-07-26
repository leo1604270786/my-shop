package com.zys.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.web.ui.api.UsersApi;
import com.zys.my.shop.web.ui.constant.SystemConstant;
import com.zys.my.shop.web.ui.dto.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: my-shop
 * @description: 登录控制器
 * @author: Leo
 * @create: 2019-06-22 22:04
 **/
@Controller
public class LoginController {
    /** 
    * @Description: 跳转到登录页 
    * @return: java.lang.String 
    * @Author: Leo 
    */ 
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    /** 
    * @Description: 登录 
    * @Param: [tbUser,model,request]
    * @return: java.lang.String 
    * @Author: Leo 
    */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        //对比验证码失败
        if (!checkVerification(tbUser, request)){
            model.addAttribute("baseResult",BaseResult.fail("验证码错误，请重新输入！"));
            return "login";
        }
        TbUser user = UsersApi.login(tbUser);
        //登录失败
        if (user == null){
            model.addAttribute("baseResult",BaseResult.fail("用户名或密码错误，请重新输入！"));
            return "login";
        }
        //登录成功
        else {
            //将会员信息放入session
            request.getSession().setAttribute(SystemConstant.SESSION_USER_KEY,user);
            return "redirect:/index";
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
        return "redirect:/index";
    }
    /**
    * @Description: 对比验证码
    * @Param: [tbUser, request]
    * @return: boolean
    * @Author: Leo
    */
    private boolean checkVerification(TbUser tbUser, HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //对比session中 与 用户提交的验证码 如果一致
        if (verification.equals(tbUser.getVerification())){
            return true;
        }
        return false;
    }
}