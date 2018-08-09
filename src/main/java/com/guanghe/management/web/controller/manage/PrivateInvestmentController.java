package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.PrivateInvestmentBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.PrivateInvestmentService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/privateInvestment")
public class PrivateInvestmentController extends BaseCotroller {

    @Resource
    private PrivateInvestmentService privateInvestmentService;


    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateInvestment/privateInvestment_list");
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateInvestment/privateInvestment_add");
        return view;
    }

    @RequestMapping("/details")
    public ModelAndView details(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateInvestment/private_investment_details");
        return view;
    }

    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public void queryPrivateInvestmentList(HttpServletResponse response,Integer pageNo, Integer pageSize,Integer investmentPoinId,
       Integer productTermId,Integer riskLevelId,Integer incomeTypeId,String sortType,String privateInvestmentName){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        map.put("investmentPoinId",investmentPoinId);
        map.put("productTermId",productTermId);
        map.put("riskLevelId",riskLevelId);
        map.put("incomeTypeId",incomeTypeId);
        map.put("sortType",sortType);
        map.put("privateInvestmentName",privateInvestmentName);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",privateInvestmentService.queryPrivateInvestmentList(map));
        resultMap.put("count",privateInvestmentService.queryPrivateInvestmentCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    /**
     * 根据id查询详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryPrivateInvestmentById(HttpServletResponse response,Integer id){
//
        if (id == null){
            return;
        }

        PrivateInvestmentBO bo = privateInvestmentService.queryPrivateInvestmentById(id);
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
    public void deletePrivateInvestment(HttpServletResponse response, Integer id){
        if (id == null){
            return;
        }

        PrivateInvestmentBO bo = privateInvestmentService.queryPrivateInvestmentById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentService.deletePrivateInvestment(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增
     * @param bo
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public void addPrivateInvestment(HttpServletResponse response, PrivateInvestmentBO bo){
        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if( StringUtils.isEmpty(bo.getFundName()) || StringUtils.isEmpty(bo.getFundType())
                || StringUtils.isEmpty(bo.getOperations()) || StringUtils.isEmpty(bo.getFundManager())
                || StringUtils.isEmpty(bo.getFundCustodian()) || StringUtils.isEmpty(bo.getProductScale())
                || StringUtils.isEmpty(bo.getSubscribeStartingPoint()) || StringUtils.isEmpty(bo.getProductTerm())
                || StringUtils.isEmpty(bo.getFundInvestment())
                || StringUtils.isEmpty(bo.getComparisonDatum()) || StringUtils.isEmpty(bo.getPerformanceReward())
                || bo.getStartTime() == null || bo.getEndTime() == null
                || bo.getAmountOfInvestment() == null || bo.getIncreasingAmount() == null
                || bo.getProductScaleStart() == null || bo.getProductScaleEnd() == null
                || StringUtils.isEmpty(bo.getCapitalCost())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentService.addPrivateInvestment(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改
     * @param bo
     */
    @RequestMapping("/update")
    public void updatePrivateInvestment(HttpServletResponse response, PrivateInvestmentBO bo){


        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(bo.getFundName()) || StringUtils.isEmpty(bo.getFundType()) || StringUtils.isEmpty(bo.getOperations())
        || StringUtils.isEmpty(bo.getFundManager()) || StringUtils.isEmpty(bo.getFundCustodian()) || StringUtils.isEmpty(bo.getProductScale())
        || StringUtils.isEmpty(bo.getSubscribeStartingPoint()) || StringUtils.isEmpty(bo.getProductTerm()) || StringUtils.isEmpty(bo.getFundInvestment())
        || StringUtils.isEmpty(bo.getCapitalCost()) || StringUtils.isEmpty(bo.getComparisonDatum()) || StringUtils.isEmpty(bo.getPerformanceReward())
        || bo.getStartTime() == null || bo.getEndTime() == null || bo.getAmountOfInvestment() == null || bo.getIncreasingAmount() == null
        || bo.getProductScaleStart() == null || bo.getProductScaleEnd() == null || bo.getInvestmentPoinId() == null || bo.getProductTermId() == null
        || bo.getRiskLevelId() == null || bo.getIncomeTypeId() == null || bo.getId() == null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        PrivateInvestmentBO newBo = privateInvestmentService.queryPrivateInvestmentById(bo.getId());
        if(newBo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }


        privateInvestmentService.updatePrivateInvestmentBO(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    @RequestMapping("/setRecommendTime")
    public void setRecommendTime(HttpServletResponse response, Integer id){

        if(id == null || id == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentService.setRecommendTime(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    @RequestMapping("/recommendList")
    public void queryPrivateInvestmentListByRecommendTime(HttpServletResponse response){

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(privateInvestmentService.queryPrivateInvestmentListByRecommendTime()));

        safeTextPrint(response, json);
    }

}