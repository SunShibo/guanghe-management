package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.IndustryInformationBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.IndustryInformationService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.OssUploadFileUtil;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/industryInformation")
public class IndustryInformationController extends BaseCotroller {

    @Resource
    private IndustryInformationService industryInformationService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/industryInformation/industryInformation_list");
        return view;
    }

    @RequestMapping("/findOne")
    public ModelAndView findOne(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/industryInformation/industryInformation_update");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        sput("base_image", SystemConfig.getString("image_bucketName"));
        view.setViewName("/industryInformation/industryInformation_add");
        return view;
    }

    /**
     * 查询行业资讯列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryIndustryInformationList(HttpServletResponse response,Integer pageNo, Integer pageSize,String title){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("title",title);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",industryInformationService.queryIndustryInformationList(map));
        resultMap.put("count",industryInformationService.queryIndustryInformationCount());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        safeTextPrint(response, json);
    }

    /**
     * 查询行业资讯详情
     * @param newsId
     */
    @RequestMapping("/detail")
    public void queryIndustryInformationById(HttpServletResponse response,Integer newsId){

        if (newsId == null){
            return;
        }

        IndustryInformationBO news = industryInformationService.queryIndustryInformationById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
        safeTextPrint(response, json);

    }


    /**
     * 删除行业资讯
     * @param newsId
     */
    @RequestMapping("/delete")
    public void deleteIndustryInformation(HttpServletResponse response, Integer newsId){
        if (newsId == null){
            return;
        }

        IndustryInformationBO news = industryInformationService.queryIndustryInformationById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        OssUploadFileUtil.deleteFileInfo(SystemConfig.getString("image_bucketName"), news.getImgUrl());
        industryInformationService.deleteIndustryInformation(newsId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增行业资讯
     * @param news
     */
    @RequestMapping("/add")
    public void addIndustryInformation(HttpServletResponse response, IndustryInformationBO news){
        String planItemJson = news.getContent().replaceAll("&quot;", "\"");
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(news.getTitle()) || StringUtils.isEmpty(news.getEnglishTitle())
                || StringUtils.isEmpty(news.getImgUrl())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getContent())
                || StringUtils.isEmpty(news.getSynopsis())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        news.setContent(planItemJson);
        industryInformationService.addIndustryInformation(news);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


    /**
     * 修改行业资讯
     * @param news
     */
    @RequestMapping("/update")
    public void updateIndustryInformation(HttpServletResponse response, IndustryInformationBO news){
        IndustryInformationBO newsDetail = industryInformationService.queryIndustryInformationById(news.getId());
        String planItemJson = news.getContent().replaceAll("&quot;", "\"");
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(news.getTitle()) || StringUtils.isEmpty(news.getEnglishTitle())
                || StringUtils.isEmpty(news.getImgUrl())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getContent())
                || StringUtils.isEmpty(news.getSynopsis()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        newsDetail.setTitle(news.getTitle());
        newsDetail.setEnglishTitle(news.getEnglishTitle());
        newsDetail.setImgUrl(news.getImgUrl());
        newsDetail.setSource(news.getSource());
        newsDetail.setContent(planItemJson);
        newsDetail.setSynopsis(news.getSynopsis());

        industryInformationService.updateIndustryInformationBO(newsDetail);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
}