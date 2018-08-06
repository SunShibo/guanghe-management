package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.BusinessSchoolDetailsBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.BusinessSchoolDetailsService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yxw on 2018/7/23.
 */
@Controller
@RequestMapping("/BusinessSchoolDetails")
public class BusinessSchoolDetailsController extends BaseCotroller {
    @Autowired
    private BusinessSchoolDetailsService businessSchoolDetailsService;
    @RequestMapping("/list")
    public ModelAndView queryBusinessSchoolDetailsList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/business_school_details");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteBigEvent(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        BusinessSchoolDetailsBo news =businessSchoolDetailsService.queryHomePageDetail(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            businessSchoolDetailsService.deleteHomePageDetail(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addBigEvent (HttpServletResponse response, BusinessSchoolDetailsBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getTitle())
                || StringUtils.isEmpty(news.getSynopsis())
                ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            businessSchoolDetailsService.addHomePageDetail(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBigEvent (HttpServletResponse response,BusinessSchoolDetailsBo news){

        BusinessSchoolDetailsBo newsDetail = businessSchoolDetailsService.queryHomePageDetail(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getTitle())
                || StringUtils.isEmpty(news.getSynopsis())  || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setTitle(news.getTitle());
            newsDetail.setSynopsis(news.getSynopsis());
            newsDetail.setImageUrl(news.getImageUrl());
            newsDetail.setCreateUser(news.getCreateUser());
            businessSchoolDetailsService.updateHomePageDetail(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/detail")
    public void queryBigEvent (HttpServletResponse response){
        BusinessSchoolDetailsBo news = businessSchoolDetailsService.queryHomePageDetail1();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
}
