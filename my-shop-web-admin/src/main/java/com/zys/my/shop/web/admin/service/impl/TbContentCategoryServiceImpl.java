package com.zys.my.shop.web.admin.service.impl;

import com.zys.my.shop.domain.TbContentCategory;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.validator.BeanValidator;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.zys.my.shop.web.admin.dao.TbContentCategoryDao;
import com.zys.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: my-shop
 * @description: 类目信息业务逻辑实现类
 * @author: Leo
 * @create: 2019-06-10 13:02
 **/
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        //验证内容信息
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            TbContentCategory parent = entity.getParentNode();
            //无父级类目 为根目录
            if (parent == null || parent.getId() == null){
                parent.setId(0L);
            }
            entity.setUpdated(new Date());
            //新增
            if (entity.getId() == null || StringUtils.isBlank(entity.getId().toString())){
                entity.setCreated(new Date());
                entity.setParent(false);
                //判断当前结点有没有父结点
                if (parent.getId() != 0L){
                    TbContentCategory currenParent = dao.getById(parent.getId());
                    //将当前结点的父节点 isParent属性设为true
                    currenParent.setParent(true);
                    dao.update(currenParent);
                }
                //无父节点
                else{
                    //当前类目设为根类目
                    entity.setParent(true);
                }
                dao.insert(entity);
            }
            //编辑
            else{
                dao.update(entity);
            }
            return BaseResult.success("保存类目分类成功");
        }
    }
}