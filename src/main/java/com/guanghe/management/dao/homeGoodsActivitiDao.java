package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.HomeActivitesBo;
import com.guanghe.management.entity.bo.HomeGoods;

import java.util.List;

/**
 * Created by yxw on 2018/9/26.
 */
public interface homeGoodsActivitiDao {
    public HomeGoods queryHomeGoods(Integer id);
    public void updateHomeGoods(HomeGoods homeGoods);
    public  void addHomeGoods(HomeGoods homeGoods);
    public void deleteHomeGoods(Integer Id);
    public List<HomeGoods> queryListInfo();
    public HomeActivitesBo queryhomeActivitesBo(Integer id);
    public void updatehomeActivitesBo(HomeActivitesBo homeActivitesBo);
    public  void addhomeActivitesBo(HomeActivitesBo homeActivitesBo);
    public void deletehomeActivitesBo(Integer Id);
    public List<HomeActivitesBo> queryhomeActivitesListInfo();
}
