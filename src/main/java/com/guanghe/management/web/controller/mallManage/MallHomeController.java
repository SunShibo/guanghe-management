package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.*;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.mallService.*;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
@Controller
@RequestMapping("/MallHome")
public class MallHomeController extends BaseCotroller {
    @Resource
    private MallBannerServise mallBannerServise;
    @Resource
    private MallImageService mallImageService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private RecommendImageService recommendImageService;
    @Resource
    private GoodsTypeService goodsTypeService;
    @RequestMapping("/list")
    public ModelAndView queryCoreTeamList(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/mall_index");
        return view;
    }
    @RequestMapping("/info")
    public void queryMallImage (HttpServletResponse response){
        List<MallImageBo> mallImageBos=mallImageService.queryMallImageInfo();
        List<MallBannerBo> mallBannerBos =mallBannerServise.queryMallBannerInfo();
        List<GoodsBo> goodsBos= goodsService.queryHomeGoodsList();
        List<RecommendImageBo> recommendImageBos =recommendImageService.queryRecommendImageInfo();
        List<GoodTypeBo> goodTypeBos = goodsTypeService.queryGoodType();
        JSONObject result = new JSONObject();
        result.put("banner", mallBannerBos);
        result.put("image",mallImageBos);
        result.put("goods",goodsBos);
        result.put("recommondImage",recommendImageBos);
        result.put("goodsType",goodTypeBos);
        result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);
    }
}
