package com.zys.my.shop.web.admin.service.impl;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.validator.BeanValidator;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zys.my.shop.web.admin.dao.TbUserDao;
import com.zys.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @program: my-shop
 * @description: 实现
 * @author: Leo
 * @create: 2019-06-02 18:58
 **/
@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        //验证用户信息
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            tbUser.setUpdated(new Date());
            //新增
            if (tbUser.getId() == null || StringUtils.isBlank(tbUser.getId().toString())){
                tbUser.setCreated(new Date());
                //对密码进行加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                dao.insert(tbUser);
            }
            //编辑
            else{
                dao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if(tbUser != null){
            //对明文密码加密
            String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());

            if (passwordMD5.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return tbUser;
    }
}