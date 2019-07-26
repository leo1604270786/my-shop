package com.zys.my.shop.web.api.service.impl;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.validator.BeanValidator;
import com.zys.my.shop.web.api.abstracts.AbstractBaseServiceImpl;
import com.zys.my.shop.web.api.dao.TbUserDao;
import com.zys.my.shop.web.api.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @program: my-shop
 * @description: 会员管理业务逻辑实现
 * @author: Leo
 * @create: 2019-06-22 20:54
 **/
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService{
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if (user != null){
            //对密码进行加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public BaseResult register(TbUser tbUser) {
        int count = count(tbUser);
        //用户名、手机或邮箱已注册
        if (count > 0){
            return BaseResult.fail("用户名、手机或邮箱已注册");
        }
        //保存用户信息
        return save(tbUser);
    }

    @Override
    public BaseResult save(TbUser entity) {
        //验证用户信息
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            entity.setUpdated(new Date());
            //新增
            if (entity.getId() == null || StringUtils.isBlank(entity.getId().toString())){
                entity.setCreated(new Date());
                //对密码进行加密
                entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
                tbUserDao.insert(entity);
            }
            //编辑
            else{
                tbUserDao.update(entity);
            }
            return BaseResult.success("保存用户信息成功",entity);
        }
    }
}