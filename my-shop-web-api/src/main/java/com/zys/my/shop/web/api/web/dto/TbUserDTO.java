package com.zys.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: my-shop
 * @description: 会员数据传输对象
 * @author: Leo
 * @create: 2019-06-22 22:19
 **/
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}