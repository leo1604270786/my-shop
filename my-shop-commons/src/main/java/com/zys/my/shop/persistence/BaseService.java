package com.zys.my.shop.persistence;

import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;

import java.util.List;

/**
 * @program: my-shop
 * @description: 业务逻辑层接口标准
 * @author: Leo
 * @create: 2019-06-12 22:21
 **/
public interface BaseService<T extends BaseEntity> {
    /**
     * @Description: 查询所有信息
     * @Author: Leo
     */
    public List<T> selectAll();
    /**
     * @Description: 保存信息
     * @Param: tbUser
     * @return: void
     * @Author: Leo
     */
    public BaseResult save(T entity);
    /**
     * @Description: 删除
     * @Param: [id]
     * @Author: Leo
     */
    public void delete(long id);
    /**
     * @Description: 通过id获取信息
     * @Param: [id]
     * @Author: Leo
     */
    public T getById(long id);
    /**
     * @Description: 更新信息
     * @Param: [tbUser]
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
     * @Param: [start, length] 记录数开始位置 ，每页记录数的数量
     * @return: java.util.List<com.zys.my.shop.domain.TbUser>
     * @Author: Leo
     */
    public PageInfo<T> page(int draw, int start, int length, T entity);
    /**
     * @Description: 查询总记录数
     * @Param: [tbUser]
     * @return: int
     * @Author: Leo
     */
    public int count(T entity);
}