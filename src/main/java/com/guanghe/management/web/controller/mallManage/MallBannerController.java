package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.MallBannerBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.mallService.MallBannerServise;
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
@RequestMapping("/MallBanner")
public class MallBannerController extends BaseCotroller {
    @Resource
    private MallBannerServise mallBannerServise;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/banner_list");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/banner_update");
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/banner_add");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteMallImage(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        MallBannerBo news =mallBannerServise.queryMallBanner(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            mallBannerServise.deleteMallBanner(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addMallImage (HttpServletResponse response, MallBannerBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            mallBannerServise.addMallBanner(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateMallImage (HttpServletResponse response,MallBannerBo news){

        MallBannerBo newsDetail = mallBannerServise.queryMallBanner(news.getId());

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

            newsDetail.setImage(news.getImage());
            mallBannerServise.updateMallBanner(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryMallImage (HttpServletResponse response, Integer Id){


        MallBannerBo news = mallBannerServise.queryMallBanner(Id);
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


        List<MallBannerBo> news = mallBannerServise.queryMallBannerInfo();
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
}
