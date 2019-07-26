package com.zys.my.shop.web.admin.service.impl;

import com.zys.my.shop.domain.TbContent;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.validator.BeanValidator;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zys.my.shop.web.admin.dao.TbContentDao;
import com.zys.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: my-shop
 * @description: TbContentService实现类
 * @author: Leo
 * @create: 2019-06-10 14:54
 **/
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent entity) {
        //验证内容信息
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
                dao.insert(entity);
            }
            //编辑
            else{
                dao.update(entity);
            }
            return BaseResult.success("保存内容信息成功");
        }
    }
}