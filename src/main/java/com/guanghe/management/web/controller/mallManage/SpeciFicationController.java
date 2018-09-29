package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.GoodsAddRequest;
import com.guanghe.management.entity.mallBo.GoodsSpeciFication;
import com.guanghe.management.service.mallService.GoodsSpecificationService;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/5.
 */
@Controller
@RequestMapping("GoodsSpeciFication")
public class SpeciFicationController extends BaseCotroller {
    @Resource
    private GoodsSpecificationService goodsSpecificationService;
    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response,String goodsInfo,Integer id){
        if (StringUtils.isEmpty(goodsInfo)){

        }
        GoodsAddRequest goods = (GoodsAddRequest)JsonUtils.getObject4JsonString(goodsInfo, GoodsAddRequest.class,
                DateUtils.DATE_PATTERN);
        if(goods == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        // 判断详情图、规格是否为空
        if(goods.getGoodsSpeciFication() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        // 获取规格信息
        List<GoodsSpeciFication> lstSpeciFication = JsonUtils.getList4JsonArray(goods.getGoodsSpeciFication(),
                GoodsSpeciFication.class);
        for (GoodsSpeciFication speciFication:lstSpeciFication){
            speciFication.setGoodsId(id);
            speciFication.setState(1);
            speciFication.setSaleCount(0);
        }
        map.put("speciFication",lstSpeciFication);
        goodsSpecificationService.addGoodsSpeciFication(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/delete")
     public void delete (HttpServletResponse response,Integer sku){
        if (sku==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        GoodsSpeciFication goodsSpeciFication=goodsSpecificationService.queryGoodsSpeciFicationById(sku);
        if (goodsSpeciFication==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        goodsSpecificationService.deleteGoodsSpeciFication(sku);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/update")
     public void update (HttpServletResponse response, Integer sku,Integer price,String specification,Integer stock,Date preferentialStartTime,
                         Date preferentialEndTime,Integer preferentialPrice){
        if (sku==null||price==null||specification==null||stock==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if (preferentialStartTime!=null&&preferentialEndTime!=null&&preferentialPrice==null&&preferentialPrice<0)
           {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        GoodsSpeciFication news=goodsSpecificationService.queryGoodsSpeciFicationById(sku);
        news.setStock(stock);
        news.setPreferentialEndTime(preferentialEndTime);
        news.setPreferentialPrice(preferentialPrice);
        news.setPreferentialStartTime(preferentialStartTime);
        news.setSpecification(specification);
        news.setPrice(price);
        goodsSpecificationService.updateGoodsSpeciFication(news);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
    @RequestMapping("/query")
    public void query (HttpServletResponse response, Integer sku ){
        if (sku==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        GoodsSpeciFication goodsSpeciFication=goodsSpecificationService.queryGoodsSpeciFicationById(sku);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(goodsSpeciFication));
        safeTextPrint(response, json);

    }

    @RequestMapping("/updatestock")
    public void updatestock (HttpServletResponse response,Integer sku){
        if (sku==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        GoodsSpeciFication goodsSpeciFication=goodsSpecificationService.queryGoodsSpeciFicationById(sku);
        if (goodsSpeciFication==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        goodsSpeciFication.setStock(0);
        goodsSpecificationService.updateGoodsSpeciFication(goodsSpeciFication);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/updatestock1")
    public void updatestock1 (HttpServletResponse response,Integer sku){
        if (sku==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        GoodsSpeciFication goodsSpeciFication=goodsSpecificationService.queryGoodsSpeciFicationById(sku);
        if (goodsSpeciFication==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        goodsSpeciFication.setStock(1000);
        goodsSpecificationService.updateGoodsSpeciFication(goodsSpeciFication);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    @RequestMapping("/detail")
    public void detail (HttpServletResponse response,Integer id ){

    }
    @RequestMapping("/detailList")
    public void detailList (HttpServletResponse response,Integer id ){
        if (id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        List<GoodsSpeciFication> speciFications = goodsSpecificationService.queryGoodsSpeciFication(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(speciFications));
        safeTextPrint(response, json);


    }

    @RequestMapping("/page")
    public ModelAndView query(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/Specification_list");
        return view;
    }
    @RequestMapping("/update1")
    public ModelAndView update1(Integer sku){
        ModelAndView view = new ModelAndView();
        view.addObject("module", goodsSpecificationService.queryGoodsSpeciFicationById(sku));
        view.setViewName("/malHome/Specification_update");
        return view;
    }
    @RequestMapping("/SpecificationAdd")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/SpecificationAdd");
        return view;
    }
}
