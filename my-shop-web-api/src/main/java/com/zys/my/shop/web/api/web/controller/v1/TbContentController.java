package com.zys.my.shop.web.api.web.controller.v1;

import com.zys.my.shop.domain.TbContent;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.web.api.service.TbContentService;
import com.zys.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-shop
 * @description: 控制器
 * @author: Leo
 * @create: 2019-06-14 17:30
 **/
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;
        if (id == null){
            tbContent = new TbContent();
        }
        return tbContent;
    }
    /** 
    * @Description: 根据类目ID查询内容 
    * @Param: [categoryId] 
    * @return: com.zys.my.shop.dto.BaseResult 
    * @Author: Leo 
    */ 
    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findTbContentByCategoryId(@PathVariable(value = "category_id") Long categoryId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);

        if (tbContents != null && tbContents.size() > 0){
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent,dto);
                tbContentDTOS.add(dto);
            }
        }

        return BaseResult.success("成功",tbContentDTOS);
    }
}