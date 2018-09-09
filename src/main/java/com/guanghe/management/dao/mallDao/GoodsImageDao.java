package com.guanghe.management.dao.mallDao;

import com.guanghe.management.entity.mallBo.GoodsImg;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
public interface GoodsImageDao {
    public List<GoodsImg> queryGoodsImgInfoByid(Integer id);//通过商品Id查图
    public void updateGoodsImg(GoodsImg goodsImg);
    public void addGoodsImg(GoodsImg goodsImg);
    public void deleteGoodsImg(Integer id);
    public  GoodsImg queryGoodImg(Integer id);

    public void batchAddGoodsImg(Map<String, Object> map);
}
