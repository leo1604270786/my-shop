package com.zys.my.shop.web.admin.abstracts;

import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.persistence.BaseEntity;
import com.zys.my.shop.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: my-shop
 * @description: 通用控制器抽象类
 * @author: Leo
 * @create: 2019-06-13 15:31
 **/
public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>>{
    @Autowired
    protected S service;
    /**
     * @Description: 跳转到列表页
     * @Author: Leo
     */
    public abstract String list();
    /**
     * @Description:  跳转到表单页
     * @Author: Leo
     */
    public abstract String form();
    /**
     * @Description: 保存信息
     * @Author: Leo
     */
    public abstract String save(T entity, Model model, RedirectAttributes attr);
    /**
     * @Description: 删除用户信息
     * @Param: [ids]
     * @Author: Leo
     */
    public abstract BaseResult delete(String ids);
    /**
     * @Description: 分页查询
     * @Param: [request]
     * @return: java.lang.String
     * @Author: Leo
     */
    public abstract PageInfo<T> page(HttpServletRequest request, T entity);
    /** 
    * @Description: 跳转到详情页 
    * @Param: [entity] 
    * @return: java.lang.String 
    * @Author: Leo 
    */ 
    public abstract String detail(T entity);
}