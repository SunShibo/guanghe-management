package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.PrivateConsultantDetailsBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.PrivateConsultantDetailsService;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/23.
 */
@Controller
@RequestMapping("/privateConsultant")
public class PrivateConsultantDetailsController extends BaseCotroller {

    @Resource
    private UploadService uploadService;

    @Resource
    private PrivateConsultantDetailsService privateConsultantDetailsService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateConsultant/privateConsultant_list");
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/privateConsultant/privateConsultant_add");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(){
        ModelAndView view = new ModelAndView();
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        view.setViewName("/privateConsultant/privateConsultant_update");
        return view;
    }

    /**
     * 新增私享顾问
     * @param privateConsultant
     */
    @RequestMapping("/add")
    public void L(HttpServletResponse response, PrivateConsultantDetailsBO privateConsultant){
        if(privateConsultant == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        if(StringUtils.isEmpty(privateConsultant.getName()) || StringUtils.isEmpty(privateConsultant.getImgUrl())
         || StringUtils.isEmpty(privateConsultant.getGender())
         || StringUtils.isEmpty(privateConsultant.getPosition()) || StringUtils.isEmpty(privateConsultant.getSynopsis())   ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }
        int id = privateConsultantDetailsService.addPrivateConsultant(privateConsultant);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 根据Id删除私享顾问
     * @param id
     */
    @RequestMapping("/delete")
    public void deletePrivateConsultantbyId(HttpServletResponse response, int id){
        if( id == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        privateConsultantDetailsService.deletePrivateConsultantbyId(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


    /**
     * 根据修改私享顾问
     * @param privateConsultant
     */
    @RequestMapping("/update")
    public void updatePrivateConsultantbyId(HttpServletResponse response, PrivateConsultantDetailsBO privateConsultant){
        if( privateConsultant.getId() == null || privateConsultant.getId() == 0 ||
        StringUtils.isEmpty(privateConsultant.getName()) ||  StringUtils.isEmpty(privateConsultant.getImgUrl())
        || StringUtils.isEmpty(privateConsultant.getGender())
                || StringUtils.isEmpty(privateConsultant.getPosition()) || StringUtils.isEmpty(privateConsultant.getSynopsis())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常！"));
            safeTextPrint(response, json);
            return;
        }

        PrivateConsultantDetailsBO oldBO = privateConsultantDetailsService.queryPrivateConsultantDetailsById(privateConsultant.getId());
        if (oldBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004","没有找到该条记录！"));
            safeTextPrint(response, json);
            return;
        }

        privateConsultantDetailsService.updatePrivateConsultantbyId(privateConsultant);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    /**
     * 查询私享顾问列表
     * @param pageNo,pageSize
     */
    @RequestMapping("/list")
    public void queryPrivateConsultantDetailsList(HttpServletResponse response,Integer pageNo, Integer pageSize,String name){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("name", name);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(privateConsultantDetailsService.queryPrivateConsultantDetailsList(map)));
        safeTextPrint(response, json);
    }


    /**
     * 查询私享顾问详情
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

        PrivateConsultantDetailsBO wealth = privateConsultantDetailsService.queryPrivateConsultantDetailsById(id);
        if (wealth == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(wealth));
        safeTextPrint(response, json);

    }

    @RequestMapping(value = "/uploadImage", produces = {"application/json;charset=UTF-8"})
    @RequiresPermissions(value = "material:upload")
    public void uploadMaterialLibrary(@RequestParam("myFile") MultipartFile file,
                                      HttpServletResponse response) throws Exception {
        String result = uploadService.uploadMaterialLibrary(file);
        if (result==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
    }
}