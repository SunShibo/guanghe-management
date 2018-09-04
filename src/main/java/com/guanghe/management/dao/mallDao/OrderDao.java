package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.mallBo.OrderBo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/8/2.
 */
public interface OrderDao {
    //添加Order
    int addOrder(OrderBo orderBO);
    //删除Order byId
    void deleteOrderbyId(Integer id);
    //修改Order byId
    void updateOrderbyId(OrderBo orderBO);
    //查询OrderList
    List<OrderBo> queryOrderList(Map<String, Object> map);
    //查询OrderById
    OrderBo queryOrderById(Integer id);
    //查询地址数目
    int queryOrderCount(Map<String, Object> map);
    //修改订单状态
    void updateOrderStatebyId(HashMap<String, Object> map);
}
