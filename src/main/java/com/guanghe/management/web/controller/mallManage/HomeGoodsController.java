package com.guanghe.management.web.controller.mallManage;


import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.HomeGoodsService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/homeGoods")
public class HomeGoodsController extends BaseCotroller {


    @Autowired
    private HomeGoodsService homeGoodsService;

    @RequestMapping("/page")
    public ModelAndView queryCoreTeamList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/goods_list");
        return view;
    }
    @RequestMapping("/update1")
    public ModelAndView queryCoreTeamList1(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/goods_update1");
        return view;
    }
    @RequestMapping("/update2")
    public ModelAndView queryCoreTeamList2(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/goods_update2");
        return view;
    }
    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/malHome/goods_image_update");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
    @RequestMapping("/detailList")//列表页
    public void  sort(HttpServletResponse response,Integer pageNo, Integer pageSize,String title,Integer type){
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        map.put("title", title);
        map.put("type",type);
        Map<String,Object> result = new HashMap<String, Object>();

        result.put("count",homeGoodsService.getHomeGoodsCount(map));
        result.put("data",homeGoodsService.getHomeGoodsList(map));

        result.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);

    }


    @RequestMapping("/updateState")
    public void updateState(HttpServletResponse response, Integer id,Integer homeState){

        if(homeState == null || id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("homeState", homeState);
        homeGoodsService.updateState(map);
        if (homeState==0) {
            homeGoodsService.deleteInfo(id);
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    @RequestMapping("/updateImgUrl")
    public void updateImgUrl(HttpServletResponse response, Integer id,String image){

        if(StringUtils.isEmpty(image) || id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("image",image);
        homeGoodsService.updateImgUrl(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

    @RequestMapping("/details")
    public void details(HttpServletResponse response, int id){

        if( id == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        Map<String, Object> map = homeGoodsService.details(id);
        map.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        safeTextPrint(response, json);
    }
    @RequestMapping("/add")
    public void add(HttpServletResponse response, Integer id,String imgUrl,Integer sort,Integer status){
        if (id==null||imgUrl==null||sort==null|status==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        Integer s=homeGoodsService.getImgUrlCount(id);
        if (s!=0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("homeState",status);
        map.put("image",imgUrl);
        map.put("sort",sort);
        homeGoodsService.updateState(map);
        homeGoodsService.createImgUrl(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }


}
