package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.CoreTeamBo;
import com.guanghe.management.entity.bo.EmployeeBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.CoreTeamService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxw on 2018/7/18.
 */
@Controller
@RequestMapping("/CoreTeam")
public class CoreTeamController  extends BaseCotroller{
    @Autowired
    private CoreTeamService coreTeamService;
    @RequestMapping("/page")
    public ModelAndView queryBigEventList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/guangheon/CoreTeam");
        return view;
    }
    @RequestMapping("toupdate")
    public  ModelAndView toupdate(Integer id){
        ModelAndView view =new ModelAndView();
        view.setViewName("/guangheon/CoreTeamUpdate");
        view.addObject("module", coreTeamService.queryCoreTeam(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");

        return  view;
    }
    @RequestMapping("toAdd")
    public  ModelAndView add(Integer id){
        ModelAndView view =new ModelAndView();
        view.setViewName("/guangheon/CoreTeamAdd");
        return  view;
    }
    @RequestMapping("/delete")
    public void deleteCoreTeam(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        EmployeeBo news =coreTeamService.queryCoreTeam(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            coreTeamService.deleteCoreTeam(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }
    }

    @RequestMapping("/add")
    public void addCoreTeam (HttpServletResponse response, EmployeeBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);

        }else{
            coreTeamService.addCoreTeam(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateCoreTeam (HttpServletResponse response,EmployeeBo news){
        EmployeeBo newsDetail = coreTeamService.queryCoreTeam(news.getId());

        if(news == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(news.getId()==null||news.getEmployeeName()==null||news.getEmployeePosition()==null||news.getImageUrl()
                ==null||news.getIntroduction()==null)
        {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{

            newsDetail.setEmployeeName(news.getEmployeeName());
            newsDetail.setEmployeePosition(news.getEmployeePosition());
            newsDetail.setImageUrl(news.getImageUrl());
            newsDetail.setIntroduction(news.getIntroduction());
            coreTeamService.updateCoreTeam(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public void queryCoreTeam (HttpServletResponse response){
        CoreTeamBo news = coreTeamService.queryHomeCoreTeam();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }
        List<Object> list =new ArrayList<Object>();
        list.add(news);
        List<EmployeeBo> employeeBo =coreTeamService.queryEmloyee();
        if (employeeBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }
        JSONObject result = new JSONObject();
        result.put("coreTeam",list);
        result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
        result.put("employee",employeeBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);

    }
}
