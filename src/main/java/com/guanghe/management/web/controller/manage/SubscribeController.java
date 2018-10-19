package com.guanghe.management.web.controller.manage;

import com.guanghe.management.entity.bo.SubscribeBo;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.AccountBo;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.SubscribeService;
import com.guanghe.management.service.mallService.AccountService;
import com.guanghe.management.service.mallService.bindingService;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yxw on 2018/9/29.
 */
@Controller
@RequestMapping("/Subscribe")
public class SubscribeController extends BaseCotroller{
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private AccountService accountService;
    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/info/subscribe");
        return view;
    }
   @RequestMapping("/updateSub")
    public ModelAndView updateSub(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/info/subscribeUpdate");
        return view;
    }


    @RequestMapping("/AccountDetail")
    public void detailInfo (HttpServletResponse response,Integer id){

        SubscribeBo newsDetail = subscribeService.queryById(id);
        AccountBo    accountBo =accountService.queryAccountByUserId(newsDetail.getUserId());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("accountBo",accountBo);
        resultMap.put("newsDetail",newsDetail);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }
    @RequestMapping("/updateInfo")
    public void updateInfo (HttpServletResponse response,SubscribeBo news){

        SubscribeBo newsDetail = subscribeService.queryById(news.getId());
        AccountBo    accountBo =accountService.queryAccountByUserId(newsDetail.getUserId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else{
            accountBo.setCounton(news.getCounton());
            accountBo.setCumulative(news.getCumulative());
            accountBo.setCollected(news.getCollected());
            accountBo.setEarn(news.getEarn());
            accountBo.setReceived(news.getReceived());
            accountService.updateAccount(accountBo);
            newsDetail.setConfirmationOfAmount(news.getConfirmationOfAmount());
            newsDetail.setAveNet(news.getAveNet());
            newsDetail.setNowNet(news.getNowNet());
            newsDetail.setMaturity(news.getMaturity());
            newsDetail.setPayCount(news.getPayCount());
            newsDetail.setStatus(2);
            subscribeService.updatestatus(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/updateSci")
    public void updatesubSci (HttpServletResponse response,SubscribeBo news){

        SubscribeBo newsDetail = subscribeService.queryById(news.getId());

        if(news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(news.getId() == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }else{
            newsDetail.setStatus(3);
            subscribeService.updatestatus(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/detail")
    public  void queryById(HttpServletResponse response,Integer id){
        if (id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        SubscribeBo subscribeBo=subscribeService.queryById(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(subscribeBo));
        safeTextPrint(response, json);
    }
    @RequestMapping("/list")
    public void queryNewsInformationList(HttpServletResponse response,Integer pageNo, Integer pageSize,String phone,Integer status){

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("phone",phone);
        if (status==1) {
            map.put("status",0);
        }
        if (status==2) {
            map.put("status", 1);
        }
        if (status==3) {
            map.put("status", 2);
        }
        if (status==4) {
            map.put("status", 3);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",subscribeService.query(map));
        resultMap.put("count",subscribeService.queryListcount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }
    @RequestMapping("/querycount")
    public void query(HttpServletResponse response){
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(subscribeService.querycount()));
        safeTextPrint(response, json);
    }

}
