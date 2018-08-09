package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.PrivateInvestmentTransactionRecordBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.PrivateInvestmentTransactionRecordService;
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
@RequestMapping("/transactionRecord")
public class PrivateInvestmentTransactionRecordController extends BaseCotroller {

    @Resource
    private PrivateInvestmentTransactionRecordService privateInvestmentTransactionRecordService;


    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateInvestment/transactionRecord_list");
        return view;
    }

    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public void queryPrivateInvestmentTransactionRecordList(HttpServletResponse response,Integer privateInvestmentId){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",privateInvestmentTransactionRecordService.queryPrivateInvestmentTransactionRecordList(privateInvestmentId));
        resultMap.put("count",privateInvestmentTransactionRecordService.queryPrivateInvestmentTransactionRecordCount(privateInvestmentId));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    /**
     * 根据id查询详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryPrivateInvestmentTransactionRecordById(HttpServletResponse response,Integer id){
//
        if (id == null){
            return;
        }

        PrivateInvestmentTransactionRecordBO bo = privateInvestmentTransactionRecordService.queryPrivateInvestmentTransactionRecordById(id);
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
    public void deletePrivateInvestmentTransactionRecord(HttpServletResponse response, Integer id){
        if (id == null){
            return;
        }

        PrivateInvestmentTransactionRecordBO bo = privateInvestmentTransactionRecordService.queryPrivateInvestmentTransactionRecordById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentTransactionRecordService.deletePrivateInvestmentTransactionRecord(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增
     * @param bo
     */
    @RequestMapping("/add")
    public void addPrivateInvestmentTransactionRecord(HttpServletResponse response, PrivateInvestmentTransactionRecordBO bo){
        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        if( bo.getPrivateInvestmentId() == null || StringUtils.isEmpty(bo.getName()) || StringUtils.isEmpty(bo.getInvestmentDirection())
                || StringUtils.isEmpty(bo.getProfit()) || StringUtils.isEmpty(bo.getScale())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        privateInvestmentTransactionRecordService.addPrivateInvestmentTransactionRecord(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改
     * @param bo
     */
    @RequestMapping("/update")
    public void updatePrivateInvestmentTransactionRecord(HttpServletResponse response, PrivateInvestmentTransactionRecordBO bo){
        PrivateInvestmentTransactionRecordBO newBo = privateInvestmentTransactionRecordService.queryPrivateInvestmentTransactionRecordById(bo.getId());

        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(bo.getId() == null ||  StringUtils.isEmpty(bo.getName())
                || StringUtils.isEmpty(bo.getInvestmentDirection())
                || StringUtils.isEmpty(bo.getProfit()) || StringUtils.isEmpty(bo.getScale()) ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(newBo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        newBo.setName(bo.getName());
        newBo.setPrivateInvestmentId(bo.getPrivateInvestmentId());
        newBo.setInvestmentDirection(bo.getInvestmentDirection());
        newBo.setProfit(bo.getProfit());
        newBo.setScale(bo.getScale());

        privateInvestmentTransactionRecordService.updatePrivateInvestmentTransactionRecordBO(newBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
}