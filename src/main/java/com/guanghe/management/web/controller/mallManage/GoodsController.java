package com.guanghe.management.web.controller.mallManage;


import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.*;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.UploadService;
import com.guanghe.management.service.mallService.BrandService;
import com.guanghe.management.service.mallService.GoodsService;
import com.guanghe.management.service.mallService.GoodsTypeService;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
@RequestMapping("/Goods")
public class GoodsController extends BaseCotroller {
    @Resource
    private GoodsService goodsService;
    @Resource
    private BrandService brandService;
    @Resource
    private GoodsTypeService goodsTypeService;
    @Resource
    private UploadService uploadService;
    @RequestMapping("/page")
    public ModelAndView queryCoreTeamList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/mall_goods");
        return view;
    }

    @RequestMapping("/brandpage")
    public ModelAndView update(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/goods_brand_update");
        return view;
    }
    @RequestMapping("/update")
    public ModelAndView queryupdate(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/add_mall_goods");
        return view;
    }
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(Integer id){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/goods_update");
        view.addObject("goods", goodsService.queryGoodsById(id));
        view.addObject("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        return view;
    }
    @RequestMapping("/detailList")//列表页
    public void  sort(HttpServletResponse response,GoodsResponseBo goodsResponseBo,Integer pageNo, Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        if (goodsResponseBo.getLeaveId()!=null){
            map.put("leaveId",goodsResponseBo.getLeaveId());
        }
        if (goodsResponseBo.getGoodsTypeId()!=null){
            map.put("goodsTypeId",goodsResponseBo.getGoodsTypeId());
        }
        if (goodsResponseBo.getBrandId()!=null){
            map.put("brandId",goodsResponseBo.getBrandId());
        }
        if (goodsResponseBo.getSortStatu()!=null){
            map.put("sortStatu",goodsResponseBo.getSortStatu());
        }
        if (goodsResponseBo.getWeightStatu()!=null){
            map.put("weightStatu",goodsResponseBo.getWeightStatu());
        }
        if(goodsResponseBo.getLeaveId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else {

            List<BrandBo> brandBos = brandService.queryBrandInfo(map);
            List<GoodsListBo> goodsBos = goodsService.queryGoodsInfoSort(map);
            JSONObject result = new JSONObject();
            result.put("first",goodsTypeService.queryGoodTypeById(goodsResponseBo.getLeaveId()));
            if (goodsResponseBo.getGoodsTypeId()!=null) {
                result.put("second", goodsTypeService.queryGoodTypeById(goodsResponseBo.getGoodsTypeId()));
            }
            result.put("brand", brandBos);
            result.put("goods",JsonUtils.getJsonString4JavaListDate(goodsBos, DateUtils.LONG_DATE_PATTERN));
            result.put("count",goodsService.queryGoodsCount(goodsResponseBo));
            result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
            safeTextPrint(response, json);
        }
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
    @RequestMapping("/updategoodsInfo1")//添加商品
    public void updategoodsInfo1(HttpServletResponse response,String imageUrl,Integer id,String weight,String name){
        GoodsBo goodsBo =goodsService.queryGoodsById(id);
        goodsBo.setWeight(weight);
        goodsBo.setName(name);
        goodsBo.setIntroduceImgUrl(imageUrl);
        goodsService.updateGoods(goodsBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/Add")//添加商品
    public void Add(HttpServletResponse response,String goodsInfo){
        goodsService.addGoods(goodsInfo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }
    @RequestMapping("/list")
    public void list(HttpServletResponse response,Integer pageNo, Integer pageSize,String name){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
            map.put("sname",name);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",goodsService.queryGoods(map));
        resultMap.put("count", goodsService.updateGoodsCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }
    @RequestMapping("/queryGoodsUpdateInfo")//添加商品
    public void updateGoods(HttpServletResponse response,Integer id){
        GoodsBo goodsBo =goodsService.queryGoodsById(id);
        List<GoodTypeBo> second =goodsTypeService.queryGoodTypeByPid(goodsBo.getLeaveId()); //查询当前二级
        List<GoodTypeBo> first = goodsTypeService.queryTypeById();//查询所有一级
        HashMap<String,Object> Map = new HashMap<String,Object>();
        Map.put("id",goodsBo.getGoodsTypeId());
        Map.put("pid",goodsBo.getLeaveId());
        List<BrandBo> brand = brandService.queryBrandOnclick(Map);
        JSONObject result = new JSONObject();
        result.put("second",second );
        result.put("first", first);
        result.put("brand",brand);
        result.put("goodsBo", goodsBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }
    @RequestMapping("/updateBrand")//添加商品
    public void updateBrand(HttpServletResponse response,Integer id,Integer leaveId,Integer goodsTypeId ,Integer brandId){
          if (id==null||leaveId==null||goodsTypeId==null||brandId==null){
              String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
              safeTextPrint(response, json);
              return;
          }
        GoodsBo goodsBo =goodsService.queryGoodsById(id);
        if (goodsBo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        goodsBo.setLeaveId(leaveId);
        goodsBo.setBrandId(brandId);
        goodsBo.setGoodsTypeId(goodsTypeId);
        goodsService.updateGoods(goodsBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }

    }

