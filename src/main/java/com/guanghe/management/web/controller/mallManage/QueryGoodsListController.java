package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.BrandBo;
import com.guanghe.management.entity.mallBo.GoodsListBo;
import com.guanghe.management.entity.mallBo.QueryGoodsResponsBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.BrandService;
import com.guanghe.management.service.mallService.QueryGoodsListService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/30.
 */
@Controller
@RequestMapping("/QueryGoodsList")
public class QueryGoodsListController extends BaseCotroller {
    @Resource
    private QueryGoodsListService queryGoodsListService;
    @Resource
    private BrandService brandService;
    @RequestMapping("/detailList")//列表页
    public void  sort(HttpServletResponse response,Integer pageNo, Integer pageSize,QueryGoodsResponsBo goodsResponsBo){
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        if (goodsResponsBo.getQuery()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        map.put("query",goodsResponsBo.getQuery());
        if (goodsResponsBo.getBrandId() != null ) {
            map.put("brandId",goodsResponsBo.getBrandId());
        }
        if (goodsResponsBo.getSortStatu()!=null){
            map.put("sortStatu", goodsResponsBo.getSortStatu());
        }
        if (goodsResponsBo.getWeightStatu()!=null){
            map.put("weightStatu", goodsResponsBo.getWeightStatu());
        }
        List<BrandBo> brandBos = brandService.queryBranddetail(map);
        List<GoodsListBo> listBos=queryGoodsListService.QueryGoodsList(map);
        JSONObject result = new JSONObject();
        result.put("brandBos",brandBos);
        result.put("listBos",listBos);
        result.put("count",queryGoodsListService.queryGoodsCount(goodsResponsBo));
        result.put("Url", "https://" + SystemConfig.getString("image_bucketName") + ".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }
}
