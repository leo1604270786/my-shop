package com.zys.my.shop.web.admin.web.controller;

import com.zys.my.shop.domain.TbContentCategory;
import com.zys.my.shop.dto.BaseResult;
import com.zys.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.zys.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-shop
 * @description: ContentCategory控制器
 * @author: Leo
 * @create: 2019-06-10 13:04
 **/
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {

    @ModelAttribute
    private TbContentCategory getContentCategory(Long id){
        TbContentCategory tbContentCategory = null;
        if (id != null){
            tbContentCategory = service.getById(id);
        }else{
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }
    /**
     * @Description: 跳转到类目列表页面
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> desList = new ArrayList<>();
        List<TbContentCategory> tbContentCategories = service.selectAll();
        //约定根结点parentId = 0
        sortCategoryList(tbContentCategories,desList,0L);
        model.addAttribute("tbContentCategories",desList);
        return "content_category_list";
    }
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }
    /**
     * @Description: 保存类目信息
     * @Param: [tbContentCategory, model, attr]
     * @return: java.lang.String
     * @Author: Leo
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes attr){
        BaseResult baseResult = service.save(tbContentCategory);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            attr.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }
    /**
    * @Description: 删除类目信息
    * @Param: [ids]
    * @return: com.zys.my.shop.dto.BaseResult
    * @Author: Leo
    */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)){
            service.delete(Long.parseLong(ids.split(",")[0]));
            baseResult = BaseResult.success("删除内容分类成功！");
        }
        else{
            baseResult = BaseResult.fail("删除内容分类失败！");
        }
        return baseResult;
    }

    /**
     * @Description: 得到树形结构数据
     * @return: java.util.List<com.zys.my.shop.domain.TbContentCategory>
     * @Author: Leo
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if (id == null){
            id = 0L;
        }
        List<TbContentCategory> result = service.selectByPid(id);
        return result;
    }
    /**
     * @Description: 对分类数据进行排序
     * @Param: [srcList, desList, parentId]
     * @Author: Leo
     */
    private void sortCategoryList(List<TbContentCategory> srcList, List<TbContentCategory> desList, Long parentId){
        for (TbContentCategory tbContentCategory : srcList) {
            //是当前父节点的子节点
            if (tbContentCategory.getParentNode().getId().equals(parentId)){
                desList.add(tbContentCategory);
                //如果当前结点是父节点(还有子节点)
                if (tbContentCategory.isParent()){
                    for (TbContentCategory contentCategory : srcList) {
                        if (contentCategory.getParentNode().getId().equals(tbContentCategory.getId())){
                            //递归
                            sortCategoryList(srcList,desList,tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}