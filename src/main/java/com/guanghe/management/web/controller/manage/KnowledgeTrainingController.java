package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.KnowledgeTrainingBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.KnowledgeTrainingService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxw on 2018/7/31.
 */
@Controller
@RequestMapping("/KnowledgeTraining")
public class KnowledgeTrainingController extends BaseCotroller {
    @Autowired
    private KnowledgeTrainingService knowledgeTrainingService;
    @RequestMapping("/delete")
    public void deleteKnowledgeTraining(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        KnowledgeTrainingBo news =knowledgeTrainingService.queryknowledgeTraining(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            knowledgeTrainingService.deleteknowledgeTraining(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addKnowledgeTraining (HttpServletResponse response, KnowledgeTrainingBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())
                ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            knowledgeTrainingService.addknowledgeTraining(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateKnowledgeTraining(HttpServletResponse response,KnowledgeTrainingBo news){

        KnowledgeTrainingBo newsDetail = knowledgeTrainingService.queryknowledgeTraining(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{

            newsDetail.setImage(news.getImage());
            newsDetail.setCreateUser(news.getCreateUser());
            knowledgeTrainingService.updateknowledgeTraining(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/detail")
    public void queryKnowledgeTraining (HttpServletResponse response){


        List<KnowledgeTrainingBo> news = knowledgeTrainingService.queryknowledgeTrainingDetail();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
}
