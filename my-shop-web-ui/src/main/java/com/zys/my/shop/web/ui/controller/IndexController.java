package com.zys.my.shop.web.ui.controller;

import com.zys.my.shop.web.ui.api.ContentsApi;
import com.zys.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: my-shop
 * @description: 首页控制器
 * @author: Leo
 * @create: 2019-06-14 20:07
 **/
@Controller
public class IndexController {
    /**
    * @Description: 跳转到首页
    * @Param: [model]
    * @Author: Leo
    */
    @RequestMapping(value = {"","index"} , method = RequestMethod.GET)
    public String index(Model model){
        //请求幻灯片
        requestContentsPPT(model);
        return "index";
    }
    /** 
    * @Description: 请求幻灯片
    * @Param: [model] 
    * @Author: Leo
    */ 
    private void requestContentsPPT(Model model){
        List<TbContent> ppt = ContentsApi.ppt();
        for (TbContent tbContent : ppt) {
            System.out.println(tbContent);
        }
        model.addAttribute("ppt",ppt);
    }
}