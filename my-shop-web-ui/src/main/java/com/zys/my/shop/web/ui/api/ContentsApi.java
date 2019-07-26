package com.zys.my.shop.web.ui.api;

import com.zys.my.shop.utils.HttpClientUtils;
import com.zys.my.shop.utils.MapperUtils;
import com.zys.my.shop.web.ui.dto.TbContent;

import java.util.List;

/**
 * @program: my-shop
 * @description: 内容管理api
 * @author: Leo
 * @create: 2019-06-22 19:05
 **/
public class ContentsApi {
    private static final String PPT_ID = "89";

    public static List<TbContent> findContentsById(String id){
        String result = HttpClientUtils.doGet(API.API_CONTENTS+id);
        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
    public static List<TbContent> ppt(){
        return findContentsById(PPT_ID);
    }
}