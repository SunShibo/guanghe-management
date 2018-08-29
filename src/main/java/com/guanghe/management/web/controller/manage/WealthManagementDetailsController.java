package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.WealthManagementDetailsBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.WealthManagementDetailsService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shishiming on 2018/7/23.
 */
@Controller
@RequestMapping("/wealthManagement")
public class WealthManagementDetailsController extends BaseCotroller {

    @Resource
    private WealthManagementDetailsService wealthManagementDetailsService;


    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/wealthManagement/wealthManagement_update");
        return view;
    }


    /**
     * 根据修改财富管理
     * @param wealthManagementDetailsBO
     */
    @RequestMapping("/update")
    public void updateWealthManagement(HttpServletResponse response, WealthManagementDetailsBO wealthManagementDetailsBO){

        WealthManagementDetailsBO oldBO = wealthManagementDetailsService.queryWealthManagementDetails();
        if (oldBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004","没有找到该条记录！"));
            safeTextPrint(response, json);
            return;
        }

        wealthManagementDetailsService.updateWealthManagement(wealthManagementDetailsBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


    /**
     * 查询财富管理详情
     */
    @RequestMapping("/detail")
    public void queryNewsInformation(HttpServletResponse response){
//
        WealthManagementDetailsBO wealth = wealthManagementDetailsService.queryWealthManagementDetails();
        if (wealth == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(wealth));
        safeTextPrint(response, json);

    }
}