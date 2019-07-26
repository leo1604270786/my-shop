package com.zys.my.shop.persistence;

import java.util.List;

/**
 * @program: my-shop
 * @description: 树形结构数据访问层标准
 * @author: Leo
 * @create: 2019-06-13 14:20
 **/
public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * @Description: 查询所有信息
     * @Author: Leo
     */
    public List<T> selectAll();
    /**
     * @Description:  插入一条信息
     * @Param: [tbUser]
     * @Author: Leo
     */
    public void insert(T entity);
    /**
     * @Description: 删除
     * @Param: [id]
     * @Author: Leo
     */
    public void delete(long id);
    /**
     * @Description: 通过id查询信息
     * @Param: [id]
     * @Author: Leo
     */
    public T getById(long id);
    /**
     * @Description: 更新信息
     * @Param: [entity]
     * @Author: Leo
     */
    public void update(T entity);
    /**
     * @Description: 根据父节点查询所有子节点
     * @Param: [parentId]
     * @Author: Leo
     */
    List<T> selectByPid(Long parentId);
}