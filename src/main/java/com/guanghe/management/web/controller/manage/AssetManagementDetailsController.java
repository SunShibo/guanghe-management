package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.AssetManagementDetailsBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.AssetManagementDetailsService;
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
@RequestMapping("/assetManagement")
public class AssetManagementDetailsController extends BaseCotroller {

    @Resource
    private AssetManagementDetailsService assetManagementDetailsService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/assetManagement/assetManagement_update");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    /**
     * 根据修改资产管理
     * @param asset
     */
    @RequestMapping("/update")
    public void updateAssetManagement(HttpServletResponse response, AssetManagementDetailsBO asset){
        if( asset.getImgUrl() == null ||  "".equals(asset.getImgUrl())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        AssetManagementDetailsBO oldBO = assetManagementDetailsService.queryAssetManagementDetails();
        if (oldBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004","没有找到该条记录！"));
            safeTextPrint(response, json);
            return;
        }

        assetManagementDetailsService.updateAssetManagement(asset);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    /**
     * 查询资产管理详情
     */
    @RequestMapping("/detail")
    public void queryNewsInformation(HttpServletResponse response){

        AssetManagementDetailsBO Asset = assetManagementDetailsService.queryAssetManagementDetails();
        if (Asset == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(Asset));
        safeTextPrint(response, json);

    }
}