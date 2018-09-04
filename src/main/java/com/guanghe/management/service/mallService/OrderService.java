package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.OrderDao;
import com.guanghe.management.entity.mallBo.OrderBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/8/2.
 */
@Service("orderService")
@Transactional
public class OrderService {

    @Resource
    private OrderDao orderDao;

    //添加Order
    public int addOrder(OrderBo orderBo){
        if(orderBo == null){
            return 0;
        }
        if(orderBo.getUserId() == 0 ){
            return 0;
        }
        return orderDao.addOrder(orderBo);
    }

    //删除Order byId
    public void deleteOrderbyId(Integer id){
        orderDao.deleteOrderbyId(id);
    }
    //修改Order byId
    public void updateOrderbyId(OrderBo orderBO){
        if(orderBO.getId() == 0 || orderBO.getId() == null){
            return ;
        }
        if(orderBO.getUserId() == 0 || orderBO.getUserId() == null){
            return ;
        }
        orderDao.updateOrderbyId(orderBO);

    }
    //查询OrderList
    public List<OrderBo> queryOrderList(Map<String, Object> map){
        return orderDao.queryOrderList(map);
    }

    //查询OrderById
    public OrderBo queryOrderById(Integer id){
        if(id == null || id == 0){
            return null;
        }
        return orderDao.queryOrderById(id);
    }

    public int queryOrderCount(Map<String, Object> map) {
        return orderDao.queryOrderCount(map);
    }

    public void updateOrderStatebyId(HashMap<String, Object> map) {
        orderDao.updateOrderStatebyId(map);
    }
}
