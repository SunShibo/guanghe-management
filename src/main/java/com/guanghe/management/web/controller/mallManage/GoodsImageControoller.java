package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.GoodsImg;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.service.mallService.GoodsImageService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/GoodsImage")
public class GoodsImageControoller extends BaseCotroller {
    @Resource
    private GoodsImageService goodsImageService;
    @Resource
    private UploadService uploadService;
    @RequestMapping("/page")
    public ModelAndView queryCoreTeamList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/goods_image");
        return view;
    }
    @RequestMapping("/toUpdate")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/goods_add_image");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteBrand(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        GoodsImg news =goodsImageService.queryGoodImg(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else {
            goodsImageService.deleteGoodsImg(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
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
    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response, String image,Integer status,Integer goodsId ){
        GoodsImg goodsImg =new GoodsImg();
        if (image!=null||status!=null||goodsId!=null){
            goodsImg.setGoodsId(goodsId);
            goodsImg.setImgUrl(image);
            goodsImg.setStatus(status);
        }else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        goodsImageService.addGoodsImg(goodsImg);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

        }


    @RequestMapping("/update")
    public void updateBrand (HttpServletResponse response,GoodsImg news){

        GoodsImg newsDetail = goodsImageService.queryGoodImg(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImgUrl())
                || StringUtils.isEmpty(news.getCreateUser()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setImgUrl(news.getImgUrl());
            newsDetail.setGoodsId(news.getGoodsId());
            newsDetail.setCreateUser(news.getCreateUser());
            goodsImageService.updateGoodsImg(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/detail")
    public void queryBrand (HttpServletResponse response,Integer id){
        List<GoodsImg> news = goodsImageService.queryGoodsImgInfoByid(id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("news",news);
            resultMap.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

            safeTextPrint(response, json);


        }
    }
}
