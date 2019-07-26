package com.zys.my.shop.web.api.service;

import com.zys.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService{
    /**
     * @Description: 根据类别ID查询内容列表
     * @Param: [categoryId]
     * @return: java.util.List<com.zys.my.shop.domain.TbContent>
     * @Author: Leo
     */
    public List<TbContent> selectByCategoryId(Long categoryId);
}
