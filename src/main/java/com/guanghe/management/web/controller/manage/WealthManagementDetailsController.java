package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.WealthManagementDetailsBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.WealthManagementDetailsService;
import com.guanghe.management.util.JsonUtils;
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
        view.setViewName("/wealth/wealth_management");
        return view;
    }

    /**
     * 新增财富管理
     * @param wealthManagement
     */
    @RequestMapping("/add")
    public void L(HttpServletResponse response, WealthManagementDetailsBO wealthManagement){
        if(wealthManagement == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(wealthManagement.getTitle()) || StringUtils.isEmpty(wealthManagement.getSubTitle())
                || StringUtils.isEmpty(wealthManagement.getEnglishSubTitle())
                || StringUtils.isEmpty(wealthManagement.getSynopsis()) ||  StringUtils.isEmpty(wealthManagement.getContent())
                || StringUtils.isEmpty(wealthManagement.getImgUrl()) || StringUtils.isEmpty(wealthManagement.getCreateUser())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        int id = wealthManagementDetailsService.addWealthManagement(wealthManagement);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 根据Id删除财富管理
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteWealthManagementbyId(HttpServletResponse response, int id){
        if( id == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        wealthManagementDetailsService.deleteWealthManagementbyId(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


    /**
     * 根据修改财富管理
     * @param wealthManagementDetailsBO
     */
    @RequestMapping("/update")
    public void updateWealthManagementbyId(HttpServletResponse response, WealthManagementDetailsBO wealthManagementDetailsBO){
        if( wealthManagementDetailsBO.getId() == null || wealthManagementDetailsBO.getId() == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        WealthManagementDetailsBO oldBO = wealthManagementDetailsService.queryWealthManagementDetailsById(wealthManagementDetailsBO.getId());
        if (oldBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004","没有找到该条记录！"));
            safeTextPrint(response, json);
            return;
        }

        wealthManagementDetailsService.updateWealthManagementbyId(wealthManagementDetailsBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    /**
     * 查询财富管理列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryWealthManagementDetailsList(HttpServletResponse response,Integer pageNo, Integer pageSize){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(wealthManagementDetailsService.queryWealthManagementDetailsList(map)));
        safeTextPrint(response, json);
    }


    /**
     * 查询财富管理详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryNewsInformationById(HttpServletResponse response,Integer id){
//
        if (id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        WealthManagementDetailsBO wealth = wealthManagementDetailsService.queryWealthManagementDetailsById(id);
        if (wealth == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(wealth));
        safeTextPrint(response, json);

    }
}