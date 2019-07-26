package com.zys.my.shop.web.admin.dao;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.persistence.BaseDao;
import org.springframework.stereotype.Repository;

/**
* @Description: 数据库表对应实体dao接口
* @Author: Leo
*/
@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /** 
    * @Description: 通过邮箱查询用户信息 
    * @Param: [email] 
    * @return: com.zys.my.shop.domain.TbUser 
    * @Author: Leo 
    */ 
    public TbUser getByEmail(String email);

}
