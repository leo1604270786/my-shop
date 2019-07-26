package com.zys.my.shop.web.admin.abstracts;

import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.persistence.BaseDao;
import com.zys.my.shop.persistence.BaseEntity;
import com.zys.my.shop.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: my-shop
 * @description: 非树形结构业务逻辑层通用实现类
 * @author: Leo
 * @create: 2019-06-13 15:08
 **/
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {
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
    public void delete(long id){
        dao.delete(id);
    }
    /**
     * @Description: 通过id获取信息
     * @Param: [id]
     * @Author: Leo
     */
    @Override
    public T getById(long id){
        return dao.getById(id);
    }
    /**
     * @Description: 更新信息
     * @Param: [tbUser]
     * @Author: Leo
     */
    @Override
    public void update(T entity){
        dao.update(entity);
    }
    /**
     * @Description: 批量删除
     * @Param: [ids]
     * @Author: Leo
     */
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }
    /**
     * @Description: 分页查询
     * @Param: [start, length] 记录数开始位置 ，每页记录数的数量
     * @return: java.util.List<com.zys.my.shop.domain.TbUser>
     * @Author: Leo
     */
    public PageInfo<T> page(int draw, int start, int length, T entity){
        PageInfo<T> pageInfo = new PageInfo<>();
        int count = dao.count(entity);
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setError("");
        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",entity);

        pageInfo.setData(dao.page(params));
        return pageInfo;
    }
    /**
     * @Description: 查询总记录数
     * @Param: [tbUser]
     * @return: int
     * @Author: Leo
     */
    public int count(T entity){
        return dao.count(entity);
    }
}