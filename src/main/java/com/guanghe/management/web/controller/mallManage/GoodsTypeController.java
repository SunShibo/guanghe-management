package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.GoodTypeBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.GoodsTypeService;
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
@RequestMapping("/GoodsType")
public class GoodsTypeController extends BaseCotroller {
    @Resource
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/onePage")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/goodsType/goodsType_one");
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }

    @RequestMapping("/delete")
    public void deleteBrand(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        GoodTypeBo news =goodsTypeService.queryGoodTypeById(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            goodsTypeService.deleteGoodType(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/add")
    public void addBrand (HttpServletResponse response, GoodTypeBo news){
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(StringUtils.isEmpty(news.getName())||StringUtils.isEmpty(news.getSort().toString()))
        {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            goodsTypeService.addGoodType(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }

    @RequestMapping("/update")
    public void updateBrand (HttpServletResponse response,GoodTypeBo news){

        GoodTypeBo newsDetail = goodsTypeService.queryGoodTypeById(news.getId());

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
            newsDetail.setPid(news.getPid());
            newsDetail.setSort(news.getSort());
            goodsTypeService.updateGoodType(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    /*一级查二级*/
    @RequestMapping("/firstMenu")
    public  void queryFirstMenu(HttpServletResponse response,Integer id){
        List<GoodTypeBo> news =goodsTypeService.queryGoodTypeByPid(id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }

    }
    @RequestMapping("/detail")
    public void queryBrand (HttpServletResponse response){
        List<GoodTypeBo> news = goodsTypeService.queryGoodType();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }
    @RequestMapping("/queryFirst")
    public void queryFirst (HttpServletResponse response){
        List<GoodTypeBo> news = goodsTypeService.queryTypeById();
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }


    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public void queryPrivateInvestmentList(HttpServletResponse response,Integer pageNo, Integer pageSize){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }


        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",goodsTypeService.getGoodsTypeList(map));
        resultMap.put("count",goodsTypeService.getGoodsTypeListCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }

    @RequestMapping("/updateById")
    public void updateById (HttpServletResponse response,String name,Integer id,String imgUrl){

        if (id == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(imgUrl)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        GoodTypeBo bo = goodsTypeService.queryGoodTypeById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("name",name);
        map.put("imgUrl",imgUrl);

        goodsTypeService.updateById(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }




    /**
     * 查询列表
     */
    @RequestMapping("/getOneTypeList")
    public void getOneTypeList(HttpServletResponse response){

        List<Map<String,Object>> list = goodsTypeService.getOneTypeList();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));

        safeTextPrint(response, json);
    }


    @RequestMapping("/updateTwoTypeById")
    public void updateTwoTypeById (HttpServletResponse response,String name,Integer id,Integer pid){

        if (id == null || StringUtils.isEmpty(name) || pid == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        GoodTypeBo bo = goodsTypeService.queryGoodTypeById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("name",name);
        map.put("pid",pid);

        goodsTypeService.updateTwoTypeById(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }

    @RequestMapping("/addOneType")
    public void addOneType (HttpServletResponse response,String name,String imgUrl){

        if (StringUtils.isEmpty(name)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        int sort = goodsTypeService.getMaxSortByPid(0);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("sort",sort+1);
        map.put("imgUrl",imgUrl);

        goodsTypeService.addOneType(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
    @RequestMapping("/addTwoType")
    public void addTwoType (HttpServletResponse response,String name,Integer pid){

        if (StringUtils.isEmpty(name)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        int sort = goodsTypeService.getMaxSortByPid(pid);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("sort",sort+1);
        map.put("pid",pid);

        goodsTypeService.addTwoType(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }
}
