package com.zys.my.shop.web.ui.interceptor;

import com.zys.my.shop.web.ui.constant.SystemConstant;
import com.zys.my.shop.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: my-shop
 * @description: 登录拦截器
 * @author: Leo
 * @create: 2019-06-22 23:22
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TbUser user = (TbUser) request.getSession().getAttribute(SystemConstant.SESSION_USER_KEY);
        //未登录
        if (user == null){
            return true;
        }
        //已登录 拦截
        else {
            response.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}