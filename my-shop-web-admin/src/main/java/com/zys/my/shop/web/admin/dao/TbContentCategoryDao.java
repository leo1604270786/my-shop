package com.zys.my.shop.web.admin.dao;

import com.zys.my.shop.domain.TbContentCategory;
import com.zys.my.shop.persistence.BaseTreeDao;
import org.springframework.stereotype.Repository;

/**
 * @program: my-shop
 * @description: 类目信息数据库dao接口
 * @author: Leo
 * @create: 2019-06-10 13:01
 **/
@Repository
public interface TbContentCategoryDao extends BaseTreeDao<TbContentCategory> {

}