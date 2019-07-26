package com.zys.my.shop.web.ui.dto;

import lombok.Data;

/**
 * @program: my-shop
 * @description: TbContent数据传输对象
 * @author: Leo
 * @create: 2019-06-14 21:55
 **/
@Data
public class TbContent {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}