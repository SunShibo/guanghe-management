package com.guanghe.management.dao.mallDao;



import com.guanghe.management.entity.mallBo.GoodsBo;
import com.guanghe.management.entity.mallBo.GoodsDetailBo;
import com.guanghe.management.entity.mallBo.GoodsListBo;
import com.guanghe.management.entity.mallBo.GoodsResponseBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
public interface GoodsDao {
    public int queryGoodsCount(GoodsResponseBo goodsResponseBo);
    public void updateGoods(GoodsBo goodsBo);
    public void addGoods(GoodsBo goodsBo);
    public void deleteGoods(Integer Id);
    public  GoodsBo  queryGoods(Integer id);
    public List<GoodsListBo> queryGoodsInfoSort(Map<String, Object> map);//排序
    public List<GoodsBo> queryHomeGoodsList();
    public  List<GoodsDetailBo> queryGoodsdetail(Integer id);
    public  List<GoodsListBo> queryGoodsbrandsDetailList(Integer id);
}

