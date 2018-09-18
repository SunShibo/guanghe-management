package com.guanghe.management.web.controller.mallManage;


import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.AccountBo;
import com.guanghe.management.entity.mallBo.IntegralTransactionBo;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.AccountService;
import com.guanghe.management.service.mallService.IntegralTransactionService;
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
 * Created by yxw on 2018/8/2.
 */
@Controller
@RequestMapping("/Account")
public class AccountController extends BaseCotroller {
    @Resource
    private AccountService accountService;
    @Resource
    private IntegralTransactionService integralTransactionService;
    @RequestMapping("/page")
    public ModelAndView query(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/mall_account");
        return view;
    }

    @RequestMapping("/toadd")
    public ModelAndView toadd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/mall_account_add");
        return view;
    }
    @RequestMapping("/querycount")
    public ModelAndView querycount(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/mall_querycount");
        return view;
    }
    @RequestMapping("/delete")
    public void deleteAccount(HttpServletResponse response, Integer id){
        if (id == null || id == 0 ) {
            return;
        }
        AccountBo news = accountService.queryAccountByUserId(id);

        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else {
            accountService.deleteAccount(id);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeJsonPrint(response, json);
        }
    }

    @RequestMapping("/add")
    public void addAccount (HttpServletResponse response,Integer integral,Integer id,String payinfo ){
        if (integral==null||id==null||payinfo==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        AccountBo newsDetail = accountService.queryAccountByUserId(id);
        if (newsDetail==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }
        newsDetail.setIntegral(newsDetail.getIntegral() + integral);
        IntegralTransactionBo integralTransactionBo =new IntegralTransactionBo();
        integralTransactionBo.setUserId(id);
        integralTransactionBo.setDeal(integral);
        integralTransactionBo.setPayinfo(payinfo);
        integralTransactionBo.setState(1);
        integralTransactionBo.setOddintegral(newsDetail.getIntegral());
        accountService.updateIntegral(newsDetail);
        integralTransactionService.addIntegralTransaction(integralTransactionBo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeJsonPrint(response, json);
    }

    @RequestMapping("/updatePassWord")
    public void updatePassWord (HttpServletResponse response,Integer userId, String passWord){
        AccountBo newsDetail = accountService.queryAccountByUserId(userId);

        if(passWord == null||userId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            newsDetail.setPaymentPassword(passWord);
            accountService.updatePassWord(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }
    @RequestMapping("/updateIntegral")
    public void updateIntegral (HttpServletResponse response,Integer userId, Integer number){
        AccountBo newsDetail = accountService.queryAccountByUserId(userId);

        if(number == null||userId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            Integer  integral =newsDetail.getIntegral();
            newsDetail.setIntegral(integral - number);
            accountService.updateIntegral(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }
    }
    @RequestMapping("/updateAddIntegral")
    public void updateAddIntegral (HttpServletResponse response,Integer userId, Integer number){
        AccountBo newsDetail = accountService.queryAccountByUserId(userId);

        if(number == null||userId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
        }
        else if(newsDetail == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }else{
            Integer  integral =newsDetail.getIntegral();
            newsDetail.setIntegral(integral+number);
            accountService.updateAddIntegral(newsDetail);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            safeTextPrint(response, json);
        }

    }

    @RequestMapping("/detail")
    public void queryAccountByUserId (HttpServletResponse response,Integer userId){
        AccountBo news = accountService.queryAccountByUserId(userId);
        if (news == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(news));
        safeTextPrint(response, json);

    }
    @RequestMapping("/list")
    public void queryList (HttpServletResponse response,Integer pageNo,Integer pageSize,String phone){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("phoneNumber",phone);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",accountService.queryList(map));
        resultMap.put("count",accountService.queryCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        safeJsonPrint(response,json);
    }

}
