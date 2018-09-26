package com.guanghe.management.service;

import com.guanghe.management.dao.homeGoodsActivitiDao;
import com.guanghe.management.entity.bo.HomeActivitesBo;
import com.guanghe.management.entity.bo.HomeGoods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/9/26.
 */
@Service("homeGoodsActivitiService")
@Transactional
public class homeGoodsActivitiService {
    @Resource
    private homeGoodsActivitiDao homeGoodsActivitiDao;
    public HomeGoods queryHomeGoods(Integer id){
        return  homeGoodsActivitiDao.queryHomeGoods(id);
    }
    public void updateHomeGoods(HomeGoods homeGoods){
        homeGoodsActivitiDao.updateHomeGoods(homeGoods);
    }
    public  void addHomeGoods(HomeGoods homeGoods){
        homeGoodsActivitiDao.addHomeGoods(homeGoods);
    }
    public void deleteHomeGoods(Integer Id){
        homeGoodsActivitiDao.deleteHomeGoods(Id);
    }
    public List<HomeGoods> queryListInfo(){
        return homeGoodsActivitiDao.queryListInfo();
    }
    public HomeActivitesBo queryhomeActivitesBo(Integer id){
        return homeGoodsActivitiDao.queryhomeActivitesBo(id);
    }
    public void updatehomeActivitesBo(HomeActivitesBo homeActivitesBo){
        homeGoodsActivitiDao.updatehomeActivitesBo(homeActivitesBo);
    }
    public  void addhomeActivitesBo(HomeActivitesBo homeActivitesBo){
        homeGoodsActivitiDao.addhomeActivitesBo(homeActivitesBo);
    }
    public void deletehomeActivitesBo(Integer Id){
        homeGoodsActivitiDao.deletehomeActivitesBo(Id);
    }
    public List<HomeActivitesBo> queryhomeActivitesListInfo(){
        return homeGoodsActivitiDao.queryhomeActivitesListInfo();
    }
}
