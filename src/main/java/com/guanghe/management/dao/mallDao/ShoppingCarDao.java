package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.mallBo.GoodsDetailBo;
import com.guanghe.management.entity.mallBo.ShoppingCarBo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/8/2.
 */
public interface ShoppingCarDao {
    //添加ShoppingCar
    int addShoppingCar(ShoppingCarBo shoppingCarBO);
    //删除ShoppingCar byId
    void deleteShoppingCarbyId(Integer id);
    //修改ShoppingCar byId
    void updateShoppingCarbyId(ShoppingCarBo shoppingCarBO);
    //查询ShoppingCarList
    List<ShoppingCarBo> queryShoppingCarList(Map<String, Object> map);
    //查询ShoppingCarById
    ShoppingCarBo queryShoppingCarById(Integer id);

    void deleteShoppingCarByIds(List<String> ids);

    List<String> queryInvalidGoodsList(HashMap<String, Object> parMap);
    List<GoodsDetailBo> queryShoppingCar(Integer id);
    void  deleteInfoList(Integer[] intTemp);
    void  deleteAll(Integer userId);
    void AddFollowList(Map<String, Object> map);
    List<Integer> queryFollow(Integer userId);

}
