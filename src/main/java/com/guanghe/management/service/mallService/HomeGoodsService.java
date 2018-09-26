package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.HomeGoodsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Service("homeGoodsService")
@Transactional
public class HomeGoodsService {
    @Resource
    private HomeGoodsDao homeGoodsDao;


    public List<HashMap<String,Object>> getHomeGoodsList(Map<String, Object> map){
        return homeGoodsDao.getHomeGoodsList(map);
    }
    public int getHomeGoodsCount(Map<String, Object> map){
        return homeGoodsDao.getHomeGoodsCount(map);
    }

    public void updateState(Map<String, Object> map) {
        homeGoodsDao.updateState(map);
    }

    public Map<String, Object> details(int id) {
        return homeGoodsDao.details(id);
    }

    public int getImgUrlCount(Integer id) {
        return homeGoodsDao.getImgUrlCount(id);
    }

    public void createImgUrl(Map<String, Object> map) {
        homeGoodsDao.createImgUrl(map);

    }

    public void updateImgUrl(Map<String, Object> map) {
        homeGoodsDao.updateImgUrl(map);
    }
    public  void  deleteInfo(Integer id){
        homeGoodsDao.deleteInfo(id);
    }

}
