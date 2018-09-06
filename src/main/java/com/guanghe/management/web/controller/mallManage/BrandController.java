package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.BrandBo;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.BrandService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/Brand")
public class BrandController extends BaseCotroller {
    @Resource
    private BrandService brandService;

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/brand/list");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteBrand(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        BrandBo news =brandService.queryBrand(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            brandService.deleteBrand(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response, BrandBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getName()))
                {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            brandService.addBrand(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBrand (HttpServletResponse response,BrandBo news){

        BrandBo newsDetail = brandService.queryBrand(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getName())
                || StringUtils.isEmpty(news.getCreateUser()) || news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setName(news.getName());
            newsDetail.setProduced(news.getProduced());
            newsDetail.setCreateUser(news.getCreateUser());
            brandService.updateBrand(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

/*    @RequestMapping("/detail")
    public void queryBrand (HttpServletResponse response,Integer id){
        List<BrandBo> news = brandService.queryBrandInfo(id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }*/
    @RequestMapping("/onclickdetail")
    public void queryBrandOnclick (HttpServletResponse response,Integer id,Integer pid) {
        HashMap<String,Object> Map = new HashMap<String,Object>();
        if (id!=null||pid!=null){
            Map.put("id",id);
            Map.put("pid",pid);
        }
        List<BrandBo> news = brandService.queryBrandOnclick(Map);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }

    @RequestMapping("/list")
    public void getBrandList(HttpServletResponse response,Integer pageNo, Integer pageSize,String name){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        if(!StringUtils.isEmpty(name)){
            map.put("title", name);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",brandService.getBrandList(map));
        resultMap.put("count",brandService.getBrandListCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }


    @RequestMapping("/updateById")
    public void updateById (HttpServletResponse response,String name,Integer id,String produced,Integer goodsTypeId,Integer levelId){

        if (id == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(produced)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        BrandBo bo = brandService.queryBrand(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("name",name);
        map.put("produced",produced);
        map.put("goodsTypeId",goodsTypeId);
        map.put("levelId",levelId);

        brandService.updateById(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }



    @RequestMapping("/insertBrand")
    public void insertBrand (HttpServletResponse response,String name,String produced,Integer goodsTypeId,Integer levelId){

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(produced)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("produced",produced);
        map.put("goodsTypeId",goodsTypeId);
        map.put("levelId",levelId);

        brandService.insertBrand(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
}
