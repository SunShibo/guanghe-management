package com.guanghe.management.dao.mallDao;

import com.guanghe.management.entity.mallBo.GoodsSpeciFication;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/5.
 */
public interface GoodsSpeciFicationDao {
    public void addGoodsSpeciFication(Map<String,Object> map);
    public void deleteGoodsSpeciFication(Integer Id);
    public List<GoodsSpeciFication> queryGoodsSpeciFication(Integer id);
    public GoodsSpeciFication queryGoodsSpeciFicationById(Integer id);
    public  void updateGoodsSpeciFication(GoodsSpeciFication goodsSpeciFication);
    public  GoodsSpeciFication addSpeciFication(GoodsSpeciFication goodsSpeciFication);
}
