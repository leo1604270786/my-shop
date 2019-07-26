package com.zys.my.shop.web.admin.web.controller;

import com.zys.my.shop.domain.TbContent;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.dto.PageInfo;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zys.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
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
 * @description: 内容信息控制器
 * @author: Leo
 * @create: 2019-06-10 15:50
 **/
@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService> {

    @ModelAttribute
    private TbContent getTbContent(Long id){
        TbContent tbContent = null;
        if (id != null){
            tbContent = service.getById(id);
        }else{
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * @Description: 跳转到内容列表页
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }
    /**
     * @Description:  跳转到内容表单页
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     * @Description: 保存内容信息
     * @Param: [tbContent]
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes attr){
        BaseResult baseResult = service.save(tbContent);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            attr.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }
    /**
     * @Description: 删除内容信息
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
            baseResult = BaseResult.success("删除内容成功！");
        }
        else{
            baseResult = BaseResult.fail("删除内容失败！");
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
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){

        String strDraw = request.getParameter("draw");
        String strStart= request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        //封装 DataTables 需要的数据
        PageInfo<TbContent> pageInfo = service.page(draw, start, length,tbContent);
        return pageInfo;
    }
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }
}