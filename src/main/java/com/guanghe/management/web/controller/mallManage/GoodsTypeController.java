package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.GoodTypeBo;
import com.guanghe.management.service.mallService.GoodsTypeService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/GoodsType")
public class GoodsTypeController extends BaseCotroller {
    @Resource
    private GoodsTypeService goodsTypeService;
    @RequestMapping("/delete")
    public void deleteBrand(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        GoodTypeBo news =goodsTypeService.queryGoodTypeById(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            goodsTypeService.deleteGoodType(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response, GoodTypeBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getName())||StringUtils.isEmpty(news.getSort().toString()))
        {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            goodsTypeService.addGoodType(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBrand (HttpServletResponse response,GoodTypeBo news){

        GoodTypeBo newsDetail = goodsTypeService.queryGoodTypeById(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getName())
                || StringUtils.isEmpty(news.getCreateUser()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setName(news.getName());
            newsDetail.setPid(news.getPid());
            newsDetail.setSort(news.getSort());
            goodsTypeService.updateGoodType(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    /*一级查二级*/
    @RequestMapping("/firstMenu")
    public  void queryFirstMenu(HttpServletResponse response,Integer id){
        List<GoodTypeBo> news =goodsTypeService.queryGoodTypeByPid(id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }

    }
    @RequestMapping("/detail")
    public void queryBrand (HttpServletResponse response){
        List<GoodTypeBo> news = goodsTypeService.queryGoodType();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/queryFirst")
    public void queryFirst (HttpServletResponse response){
        List<GoodTypeBo> news = goodsTypeService.queryTypeById();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
}
