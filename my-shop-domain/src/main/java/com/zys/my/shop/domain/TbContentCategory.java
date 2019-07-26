package com.zys.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zys.my.shop.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @program: my-shop
 * @description: 分类管理
 * @author: Leo
 * @create: 2019-06-10 12:38
 **/
@Data
public class TbContentCategory extends BaseEntity {
    @Length(min = 1, max = 20, message = "分类名称程度应介于1 - 20个字符之间")
    private String name;
    private Integer status;
    @NotNull(message = "分类排序不能为空")
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private boolean isParent;
    private TbContentCategory parentNode;
}