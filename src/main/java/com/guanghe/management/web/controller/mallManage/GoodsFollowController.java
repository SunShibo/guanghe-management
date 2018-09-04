package com.guanghe.management.web.controller.mallManage;



import com.guanghe.management.entity.bo.UserBO;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.GoodsDetailBo;
import com.guanghe.management.entity.mallBo.GoodsFollowBo;
import com.guanghe.management.pop.SystemConfig;
import com.guanghe.management.service.mallService.GoodsFollowService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/GoodsFollow")
public class GoodsFollowController extends BaseCotroller {
    @Resource
    private GoodsFollowService goodsFollowService;
    @RequestMapping("/delete")
    public void deleteGoodsFollow(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        GoodsFollowBo news =goodsFollowService.queryGoodsFollow(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            goodsFollowService.deleteGoodsFollow(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }

    }
    /*
    * 查询已经关注
    * */
    @RequestMapping("/queryUserFollow")
    public  void queryUserFollow(HttpServletResponse response,HttpServletRequest request){
        UserBO userBO = super.getLoginUser(request);
    /* 2. 验证账户状态 */
        if (userBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010007", "用户未登录！"));
            super.safeJsonPrint(response, result);
            return;
        }

        List<GoodsDetailBo> goodsDetailBos =goodsFollowService.queryUserFollow(userBO.getId());
        JSONObject result = new JSONObject();
        result.put("goodsDetailBos",goodsDetailBos);
        result.put("Url","https://" + SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        safeTextPrint(response, json);

    }

    @RequestMapping("/add")
    public void addGoodsFollow (HttpServletResponse response, GoodsFollowBo news,HttpServletRequest request){
        UserBO userBO = super.getLoginUser(request);
    /* 2. 验证账户状态 */
        if (userBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010007", "用户未登录！"));
            super.safeJsonPrint(response, result);
            return;
        }
        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else if(news.getSku()==null)
        {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }else{
            news.setUserId(userBO.getId());
            goodsFollowService.addGoodsFollow(news);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }


    @RequestMapping("/detail")
    public void queryGoodsFollow(HttpServletResponse response,Integer id){
        List<GoodsFollowBo> news = goodsFollowService.queryMyGoodsFollow(id);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
            safeTextPrint(response, json);

        }
    }

}
