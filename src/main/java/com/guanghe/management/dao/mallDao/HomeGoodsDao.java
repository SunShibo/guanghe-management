package com.guanghe.management.dao.mallDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/9/7.
 */
public interface HomeGoodsDao {

    List<HashMap<String,Object>>  getHomeGoodsList(Map<String, Object> map);
    int getHomeGoodsCount(Map<String, Object> map);
    
    void updateState(Map<String, Object> map);
    
    Map<String,Object> details(int id);

    int getImgUrlCount(Integer id);
    
    void createImgUrl(Integer id);
    
    void updateImgUrl(Map<String, Object> map);
}
