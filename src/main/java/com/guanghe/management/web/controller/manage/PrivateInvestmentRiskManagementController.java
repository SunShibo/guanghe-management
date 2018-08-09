package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.PrivateInvestmentRiskManagementBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.PrivateInvestmentRiskManagementService;
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
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/riskManagement")
public class PrivateInvestmentRiskManagementController extends BaseCotroller {

    @Resource
    private PrivateInvestmentRiskManagementService privateInvestmentRiskManagementService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateInvestment/riskManagement_list");
        return view;
    }


    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public void queryPrivateInvestmentRiskManagementList(HttpServletResponse response,Integer privateInvestmentId){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",privateInvestmentRiskManagementService.queryPrivateInvestmentRiskManagementList(privateInvestmentId));
        resultMap.put("count",privateInvestmentRiskManagementService.queryPrivateInvestmentRiskManagementCount(privateInvestmentId));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    /**
     * 根据id查询详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryPrivateInvestmentRiskManagementById(HttpServletResponse response,Integer id){
//
        if (id == null){
            return;
        }

        PrivateInvestmentRiskManagementBO bo = privateInvestmentRiskManagementService.queryPrivateInvestmentRiskManagementById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(bo));
        safeTextPrint(response, json);

    }

    /**
     * 根据id删除
     * @param id
     */
    @RequestMapping("/delete")
    public void deletePrivateInvestmentRiskManagement(HttpServletResponse response, Integer id){
        if (id == null){
            return;
        }

        PrivateInvestmentRiskManagementBO bo = privateInvestmentRiskManagementService.queryPrivateInvestmentRiskManagementById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentRiskManagementService.deletePrivateInvestmentRiskManagement(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增
     * @param bo
     */
    @RequestMapping("/add")
    public void addPrivateInvestmentRiskManagement(HttpServletResponse response, PrivateInvestmentRiskManagementBO bo){
        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        if( bo.getPrivateInvestmentId() == null || StringUtils.isEmpty(bo.getLevel()) || StringUtils.isEmpty(bo.getSynopsis()) ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        privateInvestmentRiskManagementService.addPrivateInvestmentRiskManagement(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改
     * @param bo
     */
    @RequestMapping("/update")
    public void updatePrivateInvestmentRiskManagement(HttpServletResponse response, PrivateInvestmentRiskManagementBO bo){
        PrivateInvestmentRiskManagementBO newBo = privateInvestmentRiskManagementService.queryPrivateInvestmentRiskManagementById(bo.getId());

        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(bo.getId() == null || StringUtils.isEmpty(bo.getLevel())
                || StringUtils.isEmpty(bo.getSynopsis()) ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(newBo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        newBo.setLevel(bo.getLevel());
        newBo.setSynopsis(bo.getSynopsis());

        privateInvestmentRiskManagementService.updatePrivateInvestmentRiskManagementBO(newBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
}