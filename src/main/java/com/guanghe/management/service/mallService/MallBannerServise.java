package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.MallBannerDao;
import com.guanghe.management.entity.mallBo.MallBannerBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
@Service("MallBannerServise")
@Transactional
public class MallBannerServise {
    @Resource
    private MallBannerDao mallBannerDao;
    public List<MallBannerBo> queryMallBannerInfo(){
        return  mallBannerDao.queryMallBannerInfo();
    }
    public void updateMallBanner(MallBannerBo bannerBo){
         mallBannerDao.updateMallBanner(bannerBo);
    }
    public  void addMallBanner(MallBannerBo bannerBo){
        mallBannerDao.addMallBanner(bannerBo);
    }
    public void deleteMallBanner(Integer Id){
        mallBannerDao.deleteMallBanner(Id);
    }
    public  MallBannerBo  queryMallBanner(Integer Id){
        return  mallBannerDao.queryMallBanner(Id);
    }
}
