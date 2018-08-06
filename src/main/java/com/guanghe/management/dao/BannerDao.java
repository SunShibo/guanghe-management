package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.BannerBo;

import java.util.List;

/**
 * Created by yxw on 2018/7/20.
 */
public interface BannerDao {
    public List<BannerBo> queryBannerInfo();
}
