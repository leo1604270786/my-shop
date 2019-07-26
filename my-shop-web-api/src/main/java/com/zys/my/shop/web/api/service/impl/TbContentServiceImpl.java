package com.zys.my.shop.web.api.service.impl;

import com.zys.my.shop.domain.TbContent;
import com.zys.my.shop.domain.TbContentCategory;
import com.zys.my.shop.web.api.dao.TbContentDao;
import com.zys.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: my-shop
 * @description: 业务逻辑实现
 * @author: Leo
 * @create: 2019-06-14 17:28
 **/
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCategoryId(tbContent);
    }
}