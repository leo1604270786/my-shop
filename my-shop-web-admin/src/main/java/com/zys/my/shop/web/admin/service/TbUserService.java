package com.zys.my.shop.web.admin.service;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.persistence.BaseService;

import java.util.List;

/**
* @Description:  数据库表对应实体service接口
* @Author: Leo
*/
public interface TbUserService extends BaseService<TbUser> {
    /**
     * @Description: 根据邮箱和密码登录
     * @Param: [email, password]
     * @return: com.zys.my.shop.domain.TbUser
     * @Author: Leo
     */
    public TbUser login(String email, String password);
}
