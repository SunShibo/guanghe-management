package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.ActivityReservationBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.ActivityReservationService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yxw on 2018/9/27.
 */
@Controller
@RequestMapping("/ActivityReservation")
public class ActivityReservationController extends BaseCotroller {
    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/info/ActivityReservation");
        return view;
    }
    @Resource
    private ActivityReservationService activityReservationService;
    @RequestMapping("/update")
    public void updateBanner (HttpServletResponse response,ActivityReservationBo news){

        ActivityReservationBo newsDetail = activityReservationService.queryById(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else{
            newsDetail.setStatus(2);
            activityReservationService.updatestatus(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/list")
    public void queryNewsInformationList(HttpServletResponse response,Integer pageNo, Integer pageSize,String phone,Integer status){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("phone",phone);
        if (status==1) {
            map.put("status",0);
        }
        if (status==2) {
            map.put("status", 1);
        }
        if (status==3) {
            map.put("status", 2);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",activityReservationService.query(map));
        resultMap.put("count",activityReservationService.queryListcount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }
    @RequestMapping("/querycount")
    public void query(HttpServletResponse response){
       String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(activityReservationService.querycount()));
        safeTextPrint(response, json);
    }
}
