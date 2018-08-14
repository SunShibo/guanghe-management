package com.guanghe.management.web.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.guanghe.management.entity.XtgMaterialLibrary;
import com.guanghe.management.entity.bo.BannerBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.BannerService;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.OssUploadFileUtil;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/1.
 */
@Controller
@RequestMapping("/Banner")
public class BannerController extends BaseCotroller {
    @Resource
    private UploadService uploadService;
    @Resource
    private BannerService bannerService;
    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/banner_add");
        return view;
    }
    @RequestMapping("/page")
    public ModelAndView show(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/banner_list");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/banner_update");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteBanner(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        BannerBo news =bannerService.queryBanner(id);
        String imageUrl =news.getImage();
        if (imageUrl==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else {
            OssUploadFileUtil.deleteFileInfo(SystemConfig.getString("image_bucketName"), imageUrl);
        }
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else {
            bannerService.deleteBanner(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }
    @RequestMapping("/add")
    public void addBanner (HttpServletResponse response, BannerBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else{
            bannerService.addBanner(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }
    @RequestMapping(value = "/uploadImage", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions(value = "material:upload")
    public void uploadMaterialLibrary(@RequestParam("myFile") MultipartFile file,
                               HttpServletResponse response) throws Exception {
        String result = uploadService.uploadMaterialLibrary(file);
        if (result==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBanner (HttpServletResponse response,BannerBo news){

        BannerBo newsDetail = bannerService.queryBanner(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(StringUtils.isEmpty(news.getImage()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else{
            newsDetail.setImage(news.getImage());
            bannerService.updateBanner(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryBanner (HttpServletResponse response, Integer Id){


        BannerBo news = bannerService.queryBanner(Id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/list")
    public  void  quertBannerInfo(HttpServletResponse response){
        List<BannerBo> news =bannerService.queryBannerInfo();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        JSONObject result = new JSONObject();
        result.put("news",news );
        result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);

    }
}
