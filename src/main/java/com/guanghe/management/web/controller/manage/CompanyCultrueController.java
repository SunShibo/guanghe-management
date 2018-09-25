package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.CompanyCultrueBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.CompanyCultrueService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/companyCultrue")
public class CompanyCultrueController extends BaseCotroller {
    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/companyCultrue_pc");
        return view;
    }
    @RequestMapping("/pagewap")
       public ModelAndView page1(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/guangheon/companyCultrue_wap");
        return view;
    }
    @RequestMapping("/toUpdate")
    public ModelAndView page2(Integer id){
        ModelAndView view = new ModelAndView();
        view.addObject("module", companyCultrueService.queryCompanyCultrueById(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/guangheon/companyCultrue_wap_update");
        return view;
    }

    @Resource
    private CompanyCultrueService companyCultrueService;

    /**
     * 获取公司文化列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryCompanyCultrueList(HttpServletResponse response,Integer pageNo, Integer pageSize){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(companyCultrueService.queryCompanyCultrueList(map)));
        safeTextPrint(response, json);

    }

    /**
     * 获取公司文化
     * @param newsId
     */
    @RequestMapping("/detail")
    public void queryCompanyCultrueById(HttpServletResponse response,Integer newsId){

        if (newsId == null){
            return;
        }

        CompanyCultrueBO news = companyCultrueService.queryCompanyCultrueById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
        safeTextPrint(response, json);

    }
    @RequestMapping("/update1")
    public void update1(HttpServletResponse response,CompanyCultrueBO news){

        CompanyCultrueBO newsDetail = companyCultrueService.queryCompanyCultrueById(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(news.getImage())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        newsDetail.setImage(news.getImage());
        companyCultrueService.updateCompanyCultrueBO(newsDetail);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 删除公司文化
     * @param newsId
     */
    @RequestMapping("/delete")
    public void deleteCompanyCultrue(HttpServletResponse response, Integer newsId){
        if (newsId == null){
            return;
        }

        CompanyCultrueBO news = companyCultrueService.queryCompanyCultrueById(newsId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        companyCultrueService.deleteCompanyCultrue(newsId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 添加公司文化
     * @param news
     */
    @RequestMapping("/add")
    public void addCompanyCultrue(HttpServletResponse response, CompanyCultrueBO news){

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
        companyCultrueService.addCompanyCultrue(news);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/infoPc")
    public  void  queryCompanyCultrue(HttpServletResponse response){
        CompanyCultrueBO news =companyCultrueService.queryCompanyCultruePc();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        JSONObject result = new JSONObject();
        result.put("data", news);
        result.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }
    @RequestMapping("/infoWap")
    public  void  queryCompanyCultruewap(HttpServletResponse response){
        List<CompanyCultrueBO> news =companyCultrueService.queryCompanyCultrueWap();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        JSONObject result = new JSONObject();
        result.put("news", news);
        result.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }

    /**
     * 修改公司文化
     * @param news
     */
    @RequestMapping("/update")
    public void updateCompanyCultrue(HttpServletResponse response, CompanyCultrueBO news){

        CompanyCultrueBO newsDetail =companyCultrueService.queryCompanyCultruePc();

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        newsDetail.setImage(news.getImage());
        companyCultrueService.updateCompanyCultrueBO(newsDetail);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
}