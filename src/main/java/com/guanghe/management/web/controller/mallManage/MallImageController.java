package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.MallImageBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.mallService.MallImageService;
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
@RequestMapping("/MallImage")
public class MallImageController extends BaseCotroller {
    @Resource
    private MallImageService mallImageService;

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/mall_image_update");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteMallImage(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        MallImageBo news =mallImageService.queryMallImage(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            mallImageService.deleteMallImage(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addMallImage (HttpServletResponse response, MallImageBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            mallImageService.addMallImage(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateMallImage (HttpServletResponse response,MallImageBo news){

        MallImageBo newsDetail = mallImageService.queryMallImage(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setLevelId(news.getLevelId());
            newsDetail.setGoodsTypeId(news.getGoodsTypeId());
            newsDetail.setImage(news.getImage());
            mallImageService.updateMallImage(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryMallImage (HttpServletResponse response, Integer Id){


        MallImageBo news = mallImageService.queryMallImage(Id);
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


        List<MallImageBo> news = mallImageService.queryMallImageInfo();
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
        view.setViewName("/malHome/mall_image_list");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
}
