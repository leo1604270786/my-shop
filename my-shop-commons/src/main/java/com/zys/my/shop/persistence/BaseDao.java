package com.zys.my.shop.persistence;

import java.util.List;
import java.util.Map;

/**
 * @program: my-shop
 * @description: 数据访问层接口标准
 * @author: Leo
 * @create: 2019-06-12 22:16
 **/
public interface BaseDao<T extends BaseEntity> {
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
     * @Description: 批量删除
     * @Param: [ids]
     * @Author: Leo
     */
    public void deleteMulti(String[] ids);
    /**
     * @Description: 分页查询
     * @Param: [params]  需要两个参数 start/记录数开始位置   length/每页记录数的数量
     * @Author: Leo
     */
    public List<T> page(Map<String,Object> params);
    /**
     * @Description: 查询总记录数
     * @Param: [entity]
     * @return: int
     * @Author: Leo
     */
    public int count(T entity);
}