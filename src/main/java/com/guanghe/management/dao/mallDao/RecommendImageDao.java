package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.mallBo.RecommendImageBo;

import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
public interface RecommendImageDao {
    public List<RecommendImageBo> queryRecommendImageInfo();
    public void updateRecommendImage(RecommendImageBo recommendImageBo);
    public  void addRecommendImage(RecommendImageBo recommendImageBo);
    public void deleteRecommendImage(Integer Id);
    public RecommendImageBo queryRecommendImage(Integer Id);
}

