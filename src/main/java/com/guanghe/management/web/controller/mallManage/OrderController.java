package com.guanghe.management.web.controller.mallManage;

import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.OrderBo;
import com.guanghe.management.query.QueryInfo;
import com.guanghe.management.service.mallService.OrderService;
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
 * Created by shishiming on 2018/8/2.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseCotroller{

    @Resource
    private OrderService orderService;


    @RequestMapping("/list")
    public ModelAndView queryOrderMasterList(String orderInfo,
                                             Integer status, Integer pageNo, Integer pageSize){
        pager = orderService.queryOrderMasterList(orderInfo, status,
                getQueryInfo(pageNo, pageSize));

        ModelAndView view = new ModelAndView();
        view.setViewName("/mall/orderDetail");
        view.addObject("lstOrder", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("orderInfo", orderInfo);
        view.addObject("status", status);
        return view;
    }
    /**
     * 查询列表
     */
    @RequestMapping("/list1")
    public void queryOrderList(HttpServletResponse response,Integer userId,Integer state,Integer pageNo, Integer pageSize){

        if(userId == null || userId == 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("userId", userId);
        map.put("state", state);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", orderService.queryOrderList(map));

        resultMap.put("count", orderService.queryOrderCount(map));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        safeTextPrint(response, json);
    }



    /**
     * 根据id查询详情
     * @param id
     */
    @RequestMapping("/detail")
    public void queryOrderById(HttpServletResponse response,Integer id){
//
        if (id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        OrderBo bo = orderService.queryOrderById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(bo));
        safeTextPrint(response, json);

    }

    /**
     * 根据id删除
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteOrderbyId(HttpServletResponse response, Integer id){
        if (id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        OrderBo bo = orderService.queryOrderById(id);
        if (bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }

        orderService.deleteOrderbyId(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 新增
     * @param bo
     */
    @RequestMapping("/add")
    public void addOrder(HttpServletResponse response, OrderBo bo){

        orderService.addOrder(bo);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }


    /**
     * 修改
     * @param bo
     */
    @RequestMapping("/update")
    public void updateOrderbyId(HttpServletResponse response, OrderBo bo){


        if(bo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        }
    @RequestMapping("/updatestatus")
    public void updateOrderstatus(HttpServletResponse response, Long orderId,Integer status){


        if(orderId == null||status==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

            List<OrderBo> orderBos =orderService.queryOrderByorderId(orderId);
        if (orderBos==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("orderId",orderId);
        map.put("status",status);

        orderService.updateOrder(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);

    }





    /**
     * 修改订单状态
     * @param id,userId,state
     */
    @RequestMapping("/updateState")
    public void updateOrderbyId(HttpServletResponse response, Integer id,Integer userId,Integer state){

        if(id == null || userId == null || state == null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            safeTextPrint(response, json);
            return;
        }

        OrderBo newBo = orderService.queryOrderById(id);
        if(newBo == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            safeTextPrint(response, json);
            return;
        }


        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("userId",userId);
        map.put("state",state);

        //修改订单状态
        orderService.updateOrderStatebyId(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        safeTextPrint(response, json);
    }

}
