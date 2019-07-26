package com.zys.my.shop.web.api.service;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.persistence.BaseService;

/**
 * @program: my-shop
 * @description: 会员管理业务逻辑接口
 * @author: Leo
 * @create: 2019-06-22 20:54
 **/
public interface TbUserService extends BaseService<TbUser>{
    /**
    * @Description: 登录
    * @Param: [tbUser]
    * @return: com.zys.my.shop.domain.TbUser
    * @Author: Leo
    */
    public TbUser login( TbUser tbUser);
    /**
    * @Description: 注册
    * @Param: [tbUser]
    * @return: com.zys.my.shop.dto.BaseResult
    * @Author: Leo
    */
    public BaseResult register( TbUser tbUser);
}
