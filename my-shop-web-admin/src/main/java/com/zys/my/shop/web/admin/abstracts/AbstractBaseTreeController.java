package com.zys.my.shop.web.admin.abstracts;

import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.persistence.BaseEntity;
import com.zys.my.shop.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @program: my-shop
 * @description: 通用树形结构控制器抽象类
 * @author: Leo
 * @create: 2019-06-13 15:48
 **/
public abstract class AbstractBaseTreeController<T extends BaseEntity, S extends BaseTreeService<T>>{
    @Autowired
    protected S service;

    /**
     * @Description: 跳转到类目列表页面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Leo
     */
    public abstract String list(Model model);
    /**
    * @Description: 跳转到表单页
    * @Param: [entity]
    * @return: java.lang.String
    * @Author: Leo
    */
    public abstract String form(T entity);
    /**
     * @Description: 保存类目信息
     * @Param: [entity, model, attr]
     * @return: java.lang.String
     * @Author: Leo
     */
    public abstract String save(T entity, Model model, RedirectAttributes attr);
    /**
     * @Description: 删除类目信息
     * @Param: [ids]
     * @Author: Leo
     */
    public abstract BaseResult delete(String ids);
    /**
     * @Description: 得到树形结构数据
     * @Author: Leo
     */
    public abstract List<T> treeData(Long id);
}