package com.guanghe.management.web.controller.mallManage;


import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.RecommendImageBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.mallService.RecommendImageService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/7.
 */
@Controller
@RequestMapping("/RecommendImage")
public class RecommendImageController extends BaseCotroller{
    @Resource
    private RecommendImageService recommendImageService;

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/recommend_image_update");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteMallImage(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        RecommendImageBo news =recommendImageService.queryRecommendImage(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            recommendImageService.deleteRecommendImage(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addMallImage (HttpServletResponse response, RecommendImageBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            recommendImageService.addRecommendImage(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateMallImage (HttpServletResponse response,RecommendImageBo news){

        RecommendImageBo newsDetail = recommendImageService.queryRecommendImage(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage()) || news.getGoodsTypeId() == null|| news.getLevelId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setGoodsTypeId(news.getGoodsTypeId());
            newsDetail.setImage(news.getImage());
            newsDetail.setLevelId(news.getLevelId());
            recommendImageService.updateRecommendImage(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryMallImage (HttpServletResponse response, Integer Id){


        RecommendImageBo news = recommendImageService.queryRecommendImage(Id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/detailList")
    public void queryMallImage (HttpServletResponse response){


        List<RecommendImageBo> news = recommendImageService.queryRecommendImageInfo();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("data",news);
            map.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");

            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/recommend_image_list");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
}
