package com.zys.my.shop.web.admin.abstracts;

import com.zys.my.shop.persistence.BaseEntity;
import com.zys.my.shop.persistence.BaseTreeDao;
import com.zys.my.shop.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: my-shop
 * @description: 树形结构业务逻辑层通用实现抽象类
 * @author: Leo
 * @create: 2019-06-13 14:32
 **/
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {
    @Autowired
    protected D dao;
    /**
     * @Description: 查询所有信息
     * @Author: Leo
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }
    /**
    * @Description: 删除
    * @Param: [id]
    * @Author: Leo
    */
    @Override
    public void delete(long id) {
        dao.delete(id);
    }
    /**
     * @Description: 通过id查询信息
     * @Param: [id]
     * @Author: Leo
     */
    @Override
    public T getById(long id){
        return dao.getById(id);
    }
    /**
     * @Description: 更新信息
     * @Param: [entity]
     * @Author: Leo
     */
    @Override
    public void update(T entity){
        dao.update(entity);
    }
    /**
     * @Description: 根据父节点查询所有子节点
     * @Param: [parentId]
     * @Author: Leo
     */
    @Override
    public List<T> selectByPid(Long parentId){
        return dao.selectByPid(parentId);
    }
}