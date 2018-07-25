package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.NewsInformationBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.NewsInformationService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/newsInformation")
public class NewsInformationController extends BaseCotroller {

    @Resource
    private NewsInformationService newsInformationService;

    /**
     * 查询新闻动态列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryNewsInformationList(HttpServletResponse response,Integer pageNo, Integer pageSize){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(newsInformationService.queryNewsInformationList(map)));
        safeTextPrint(response, json);
    }



    /**
     * 查询新闻动态详情
     * @param newsId
     */
    @RequestMapping("/detail")
    public void queryNewsInformationById(HttpServletResponse response,Integer newsId){
//
        if (newsId == null){
            return;
        }

        NewsInformationBO news = newsInformationService.queryNewsInformationById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
        safeTextPrint(response, json);

    }

    /**
     * 删除新闻动态
     * @param newsId
     */
    @RequestMapping("/delete")
    public void deleteNewsInformation(HttpServletResponse response, Integer newsId){
        if (newsId == null){
            return;
        }

        NewsInformationBO news = newsInformationService.queryNewsInformationById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        newsInformationService.deleteNewsInformation(newsId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增新闻动态
     * @param news
     */
    @RequestMapping("/add")
    public void addNewsInformation(HttpServletResponse response, NewsInformationBO news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(news.getTitle()) || StringUtils.isEmpty(news.getHeadTitle())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getContent())
                || StringUtils.isEmpty(news.getCreateNewsUser())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        newsInformationService.addNewsInformation(news);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改新闻动态
     * @param news
     */
    @RequestMapping("/update")
    public void updateNewsInformation(HttpServletResponse response, NewsInformationBO news){
        NewsInformationBO newsDetail = newsInformationService.queryNewsInformationById(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(news.getTitle()) || StringUtils.isEmpty(news.getHeadTitle())
                || StringUtils.isEmpty(news.getSource()) || StringUtils.isEmpty(news.getContent())
                || StringUtils.isEmpty(news.getCreateNewsUser()) || news.getId() == null){
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
        newsDetail.setHeadTitle(news.getHeadTitle());
        newsDetail.setSource(news.getSource());
        newsDetail.setContent(news.getContent());
        newsDetail.setCreateNewsUser(news.getCreateNewsUser());

        newsInformationService.updateNewsInformationBO(newsDetail);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
}