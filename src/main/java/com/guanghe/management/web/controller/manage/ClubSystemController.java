package com.guanghe.management.web.controller.manage;
import com.alibaba.fastjson.JSONObject;
import com.guanghe.management.entity.bo.ClubSystemBo;
import com.guanghe.management.entity.bo.PrivateClubBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.ClubSystemService;
import com.guanghe.management.service.PrivateClubService;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
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
 * Created by yxw on 2018/8/7.
 */
@Controller
@RequestMapping("/ClubSystem")
public class ClubSystemController extends BaseCotroller {
    @Resource
    private ClubSystemService clubSystemService;
    @Resource
    private UploadService uploadService;
    @RequestMapping("/page")
    public ModelAndView show(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/ClubSystem_list");
        return view;
    }
    @RequestMapping("toupdate")
    public  ModelAndView toupdate(Integer id){
        ModelAndView view =new ModelAndView();
        view.setViewName("/homeInfo/ClubSystem_update");
        view.addObject("club", clubSystemService.queryClubSystemb(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");

        return  view;
    }
    @RequestMapping("/toAdd")
    public ModelAndView show1(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/homeInfo/ClubSystem_add");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteBanner(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        ClubSystemBo news =clubSystemService.queryClubSystemb(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            clubSystemService.deleteClubSystem(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addBanner (HttpServletResponse response, ClubSystemBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())||StringUtils.isEmpty(news.getContent())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            clubSystemService.addClubSystem(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBanner (HttpServletResponse response,ClubSystemBo news){

        ClubSystemBo newsDetail = clubSystemService.queryClubSystemb(news.getId());

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
            clubSystemService.updateClubSystem(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryBanner (HttpServletResponse response, Integer Id){
        ClubSystemBo news = clubSystemService.queryClubSystemb(Id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/list")
    public void queryPrivateClubInfo(HttpServletResponse response){
        List<ClubSystemBo> news = clubSystemService.queryClubSystemBo();
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
}
