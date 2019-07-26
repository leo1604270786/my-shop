package com.zys.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zys.my.shop.persistence.BaseEntity;
import com.zys.my.shop.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;


/**
 * @program: my-shop
 * @description: 数据库表对应实体类
 * @author: Leo
 * @create: 2019-06-02 18:48
 **/
@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "用户名长度应介于6 - 20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度应介于6 - 20位之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
}