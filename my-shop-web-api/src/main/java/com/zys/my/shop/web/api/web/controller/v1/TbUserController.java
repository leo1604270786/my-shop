package com.zys.my.shop.web.api.web.controller.v1;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.web.api.service.TbUserService;
import com.zys.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-shop
 * @description: 会员管理控制器
 * @author: Leo
 * @create: 2019-06-22 21:01
 **/
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;
    
    /** 
    * @Description:  登录
    * @Param: [tbUser] 
    * @return: com.zys.my.shop.dto.BaseResult 
    * @Author: Leo 
    */ 
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if (user == null){
            return BaseResult.fail("账号或密码错误");
        }
        else {
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user,dto);
            return BaseResult.success("登录成功",dto);
        }
    }
    /**
    * @Description: 注册
    * @Param: [tbUser]
    * @return: com.zys.my.shop.dto.BaseResult
    * @Author: Leo
    */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResult register(TbUser tbUser){
        BaseResult baseResult = tbUserService.register(tbUser);
        //注册成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            TbUserDTO dto = new TbUserDTO();
            TbUser data = (TbUser) baseResult.getData();
            BeanUtils.copyProperties(data,dto);
            return BaseResult.success("注册成功",dto);
        }
        //注册失败
        else {
            return baseResult;
        }
    }
}