package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.KnowledgeTrainingBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.KnowledgeTrainingService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
@Controller
@RequestMapping("/KnowledgeTraining")
public class KnowledgeTrainingController extends BaseCotroller {
    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/school/knowledge_add");
        return view;
    }
    @RequestMapping("/page")
    public ModelAndView show(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/school/knowledge_list");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer id){
        ModelAndView view = new ModelAndView();
        view.setViewName("/school/knowledge_update");
        view.addObject("module", knowledgeTrainingService.queryknowledgeTraining(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
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
            knowledgeTrainingService.updateknowledgeTraining(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/detail")
    public void queryKnowledgeTraining (HttpServletResponse response,Integer pageNo, Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        List<KnowledgeTrainingBo> news = knowledgeTrainingService.queryknowledgeTrainingDetail(map);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }else{
            JSONObject result = new JSONObject();
            result.put("data", news);
            result.put("count",knowledgeTrainingService.queryCount());
            result.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
    }
}
