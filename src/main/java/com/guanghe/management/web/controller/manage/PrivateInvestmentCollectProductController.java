package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.PrivateInvestmentCollectProductBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.service.PrivateInvestmentCollectProductService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Controller
@RequestMapping("/collectProduct")
public class PrivateInvestmentCollectProductController extends BaseCotroller {

    @Resource
    private PrivateInvestmentCollectProductService privateInvestmentCollectProductService;


    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public void queryPrivateInvestmentCollectProductList(HttpServletResponse response,Integer privateInvestmentId){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",privateInvestmentCollectProductService.queryPrivateInvestmentCollectProductList(privateInvestmentId));
        resultMap.put("count",privateInvestmentCollectProductService.queryPrivateInvestmentCollectProductCount(privateInvestmentId));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    /**
     * 根据id查询详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryPrivateInvestmentCollectProductById(HttpServletResponse response,Integer id){
//
        if (id == null){
            return;
        }

        PrivateInvestmentCollectProductBO bo = privateInvestmentCollectProductService.queryPrivateInvestmentCollectProductById(id);
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
    public void deletePrivateInvestmentCollectProduct(HttpServletResponse response, Integer id){
        if (id == null){
            return;
        }

        PrivateInvestmentCollectProductBO bo = privateInvestmentCollectProductService.queryPrivateInvestmentCollectProductById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        privateInvestmentCollectProductService.deletePrivateInvestmentCollectProduct(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增
     * @param bo
     */
    @RequestMapping("/add")
    public void addPrivateInvestmentCollectProduct(HttpServletResponse response, PrivateInvestmentCollectProductBO bo){
        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        if(bo.getPrivateInvestmentId() == null || StringUtils.isEmpty(bo.getName()) ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        privateInvestmentCollectProductService.addPrivateInvestmentCollectProduct(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改
     * @param bo
     */
    @RequestMapping("/update")
    public void updatePrivateInvestmentCollectProduct(HttpServletResponse response, PrivateInvestmentCollectProductBO bo){
        PrivateInvestmentCollectProductBO newBo = privateInvestmentCollectProductService.queryPrivateInvestmentCollectProductById(bo.getId());

        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        if(bo.getId() == null || bo.getPrivateInvestmentId() == null || StringUtils.isEmpty(bo.getName()) ){
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

        privateInvestmentCollectProductService.updatePrivateInvestmentCollectProductBO(newBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
}