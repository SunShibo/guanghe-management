package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.AssetManagementDetailsBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.AssetManagementDetailsService;
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
 * Created by shishiming on 2018/7/23.
 */
@Controller
@RequestMapping("/assetManagement")
public class AssetManagementDetailsController extends BaseCotroller {

    @Resource
    private AssetManagementDetailsService assetManagementDetailsService;

    /**
     * 新增资产管理
     * @param asset
     */
    @RequestMapping("/add")
    public void L(HttpServletResponse response, AssetManagementDetailsBO asset){
        if(asset == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(asset.getTitle()) || StringUtils.isEmpty(asset.getContent())
                || StringUtils.isEmpty(asset.getImgUrl()) || StringUtils.isEmpty(asset.getCreateUser())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        int id = assetManagementDetailsService.addAssetManagement(asset);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 根据Id删除资产管理
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteAssetManagementbyId(HttpServletResponse response, int id){
        if( id == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        assetManagementDetailsService.deleteAssetManagementbyId(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


    /**
     * 根据修改资产管理
     * @param asset
     */
    @RequestMapping("/update")
    public void updateAssetManagementbyId(HttpServletResponse response, AssetManagementDetailsBO asset){
        if( asset.getId() == null || asset.getId() == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        AssetManagementDetailsBO oldBO = assetManagementDetailsService.queryAssetManagementDetailsById(asset.getId());
        if (oldBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004","没有找到该条记录！"));
            safeTextPrint(response, json);
            return;
        }

        assetManagementDetailsService.updateAssetManagementbyId(asset);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    /**
     * 查询资产管理列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryAssetManagementDetailsList(HttpServletResponse response,Integer pageNo, Integer pageSize){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(assetManagementDetailsService.queryAssetManagementDetailsList(map)));
        safeTextPrint(response, json);
    }


    /**
     * 查询资产管理详情
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

        AssetManagementDetailsBO Asset = assetManagementDetailsService.queryAssetManagementDetailsById(id);
        if (Asset == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(Asset));
        safeTextPrint(response, json);

    }
}