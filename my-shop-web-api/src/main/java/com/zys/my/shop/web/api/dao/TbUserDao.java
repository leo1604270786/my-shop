package com.zys.my.shop.web.api.dao;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.persistence.BaseDao;
import org.springframework.stereotype.Repository;

/**
* @Description: 会员实体dao接口
* @Author: Leo
*/
@Repository
public interface TbUserDao extends BaseDao<TbUser>{
    /** 
    * @Description: 登录 
    * @Param: [tbUser] 
    * @return: com.zys.my.shop.domain.TbUser 
    * @Author: Leo 
    */ 
    public TbUser login(TbUser tbUser);
}
