package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.*;

import java.util.List;

/**
 * Created by yxw on 2018/7/20.
 */
public interface InfoDao {
    public List<CompanyIntroductionBo> queryCompanyIntroductionInfo();
    public List<NewsInformationBO> queryNewsInformationInfo();
    public  List<ImageBo> queryImageInfo();
    public PrivateConsultantBo queryPrivateConsultantInfo();
    public WealthManagementBo queryWealthManagementInfo();
    public BusinessSchoolBo queryBusinessSchoolInfo();
    public  List<PrivateClubBo> queryPrivateClub();

}
