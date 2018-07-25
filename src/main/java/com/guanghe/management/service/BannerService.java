package com.guanghe.management.service;

import com.guanghe.management.dao.BannerDao;
import com.guanghe.management.entity.bo.BannerBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/7/20.
 */
@Service("BannerService")
@Transactional
public class BannerService {
    @Resource
    private BannerDao bannerDao;
    public List<BannerBo> queryBannerInfo(){
      return bannerDao.queryBannerInfo();
    }
}
