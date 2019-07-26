package com.zys.my.shop.web.admin.web.controller;

import com.zys.my.shop.domain.TbUser;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zys.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: my-shop
 * @description: 用户信息控制器
 * @author: Leo
 * @create: 2019-06-02 22:37
 **/
@Controller
@RequestMapping("user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @ModelAttribute
    private TbUser getTbUser(Long id){
        TbUser tbUser = null;
        if (id != null){
            tbUser = service.getById(id);
        }else{
            tbUser = new TbUser();
        }
        return tbUser;
    }
    
    /**
    * @Description: 跳转到用户列表页
    * @Author: Leo
    */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }
    /**
    * @Description:  跳转到用户表单页
    * @Author: Leo
    */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }
    
    /** 
    * @Description: 保存用户信息
    * @Param: [tbUser] 
    * @Author: Leo
    */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes attr){
        BaseResult baseResult = service.save(tbUser);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            attr.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }
    /**
    * @Description: 删除用户信息
    * @Param: [ids]
    * @return: java.lang.String
    * @Author: Leo
    */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)){
            service.deleteMulti(ids.split(","));
            baseResult = BaseResult.success("删除用户成功！");
        }
        else{
            baseResult = BaseResult.fail("删除用户失败！");
        }
        return baseResult;
    }
    /** 
    * @Description: 分页 
    * @Param: [request] 
    * @return: java.lang.String 
    * @Author: Leo 
    */
    @Override
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser){

        String strDraw = request.getParameter("draw");
        String strStart= request.getParameter("start");
        String strLength = request.getParameter("length");
        
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        //封装 DataTables 需要的数据
        PageInfo<TbUser> pageInfo = service.page(draw, start, length,tbUser);
        return pageInfo;
    }
    /**
     * @Description: 跳转到详情页
     * @Param: [entity]
     * @return: java.lang.String
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }
}