package com.guanghe.management.dao.mallDao;



import com.guanghe.management.entity.mallBo.GoodsDetailBo;
import com.guanghe.management.entity.mallBo.GoodsFollowBo;

import java.util.List;

/**
 * Created by yxw on 2018/8/2.
 */
public interface GoodsFollowDao {
    public void addGoodsFollow(GoodsFollowBo goodsFollow);
    public void deleteGoodsFollow(Integer Id);
    public List<GoodsFollowBo> queryMyGoodsFollow(Integer id);//用户查询已经关注信息
    public GoodsFollowBo queryGoodsFollow(Integer id);
    public  List<GoodsDetailBo> queryUserFollow(Integer id);
}
