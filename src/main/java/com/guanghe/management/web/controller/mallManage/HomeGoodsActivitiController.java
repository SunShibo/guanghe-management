package com.guanghe.management.web.controller.mallManage;

import com.alibaba.fastjson.JSONObject;
import com.guanghe.management.entity.bo.HomeActivitesBo;
import com.guanghe.management.entity.bo.HomeGoods;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.service.homeGoodsActivitiService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxw on 2018/9/26.
 */
@Controller
@RequestMapping("/HomeGoodsActiviti")
public class HomeGoodsActivitiController extends BaseCotroller{
    @Resource
    private homeGoodsActivitiService homeGoodsActivitiService;
    @Resource
    private UploadService uploadService;
    @RequestMapping("/page")
    public ModelAndView show(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/activiti_list");
        return view;
    }
    @RequestMapping("toupdate")
    public  ModelAndView toupdate(Integer id){
        ModelAndView view =new ModelAndView();
        view.setViewName("/homeInfo/activiti_update");
        view.addObject("club", homeGoodsActivitiService.queryhomeActivitesBo(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");

        return  view;
    }
    @RequestMapping("/page1")
    public ModelAndView show1(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/goods_list");
        return view;
    }
    @RequestMapping("toupdate1")
    public  ModelAndView toupdate1(Integer id){
        ModelAndView view =new ModelAndView();
        view.setViewName("/homeInfo/goods_update");
        view.addObject("club", homeGoodsActivitiService.queryHomeGoods(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");

        return  view;
    }



    @RequestMapping("/update")
    public void updateBanner (HttpServletResponse response,HomeActivitesBo news){

        HomeActivitesBo newsDetail = homeGoodsActivitiService.queryhomeActivitesBo(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else{
            newsDetail.setUrl(news.getUrl());
            newsDetail.setWapUrl(news.getWapUrl());
            newsDetail.setContent(news.getContent());
            newsDetail.setImage(news.getImage());
            newsDetail.setWapImage(news.getWapImage());
            homeGoodsActivitiService.updatehomeActivitesBo(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryBanner (HttpServletResponse response, Integer Id){
        HomeActivitesBo news = homeGoodsActivitiService.queryhomeActivitesBo(Id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/list")
    public void query(HttpServletResponse response){
        List<HomeActivitesBo> news = homeGoodsActivitiService.queryhomeActivitesListInfo();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            JSONObject result = new JSONObject();
            result.put("news",news );
            result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
    }
    @RequestMapping("/update1")
    public void updateBanner1 (HttpServletResponse response,HomeGoods news){

        HomeGoods newsDetail = homeGoodsActivitiService.queryHomeGoods(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else{
            newsDetail.setUrl(news.getUrl());
            newsDetail.setWapUrl(news.getWapUrl());
            newsDetail.setContent(news.getContent());
            newsDetail.setImage(news.getImage());
            newsDetail.setWapImage(news.getWapImage());
            homeGoodsActivitiService.updateHomeGoods(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail1")
    public void queryBanner1 (HttpServletResponse response, Integer Id){
        HomeGoods news = homeGoodsActivitiService.queryHomeGoods(Id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/list1")
    public void query1(HttpServletResponse response){
        List<HomeGoods> news = homeGoodsActivitiService.queryListInfo();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            JSONObject result = new JSONObject();
            result.put("news",news );
            result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
    }
}