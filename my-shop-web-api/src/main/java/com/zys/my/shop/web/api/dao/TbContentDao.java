package com.zys.my.shop.web.api.dao;

import com.zys.my.shop.domain.TbContent;
import com.zys.my.shop.persistence.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbContentDao{
    /**
    * @Description: 根据类别ID查询内容列表
    * @Param: [tbContent]
    * @return: java.util.List<com.zys.my.shop.domain.TbContent>
    * @Author: Leo
    */
    public List<TbContent> selectByCategoryId(TbContent tbContent);
}
