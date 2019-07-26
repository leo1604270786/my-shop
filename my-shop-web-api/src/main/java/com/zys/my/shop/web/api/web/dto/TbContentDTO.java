package com.zys.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: my-shop
 * @description: TbContent数据传输对象
 * @author: Leo
 * @create: 2019-06-14 18:29
 **/
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}