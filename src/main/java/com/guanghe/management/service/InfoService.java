package com.guanghe.management.service;

import com.guanghe.management.dao.InfoDao;
import com.guanghe.management.entity.bo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/7/20.
 */
@Service("InfoService")
@Transactional
public class InfoService {
    @Resource
    private InfoDao infoDao;
    public List<CompanyIntroductionBo> queryCompanyIntroductionInfo(){
        return infoDao.queryCompanyIntroductionInfo();
    }
    public List<NewsInformationBO> queryNewsInformationInfo(){
        return  infoDao.queryNewsInformationInfo();
    }
    public  List<ImageBo> queryImageInfo(){
        return  infoDao.queryImageInfo();
    }
    public List<ModuleBo> queryModuleInfo(){
        return  infoDao.queryModuleInfo();
    }
    public  List<PrivateClubBo> queryPrivateClub(){
     return  infoDao.queryPrivateClub();
    }
}
