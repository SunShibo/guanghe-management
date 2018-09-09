package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.mallBo.GoodsSpeciFication;
import com.guanghe.management.service.mallService.GoodsSpecificationService;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yxw on 2018/9/5.
 */
@Controller
@RequestMapping("GoodsSpeciFication")
public class SpeciFicationController extends BaseCotroller {
    @Resource
    private GoodsSpecificationService goodsSpecificationService;
    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response, String image,Integer status,Integer goodsId ){
    }
    @RequestMapping("/delete")
     public void delete (HttpServletResponse response, String image,Integer status,Integer goodsId ){
    }
    @RequestMapping("/update")
     public void update (HttpServletResponse response, String image,Integer status,Integer goodsId ){
    }
    @RequestMapping("/detail")
    public void detail (HttpServletResponse response, String image,Integer status,Integer goodsId ){
    }
    @RequestMapping("/page")
    public ModelAndView query(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/Specification_list");
        return view;
    }
}
