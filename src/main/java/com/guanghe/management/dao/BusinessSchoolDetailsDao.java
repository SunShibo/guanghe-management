package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.BusinessSchoolDetailsBo;

/**
 * Created by yxw on 2018/7/23.
 */
public interface BusinessSchoolDetailsDao {
     public  BusinessSchoolDetailsBo queryHomePageDetail(Integer id);
     public  void updateHomePageDetail(BusinessSchoolDetailsBo businessSchoolDetailsBo);
     public  void addHomePageDetail(BusinessSchoolDetailsBo businessSchoolDetailsBo);
     public  void deleteHomePageDetail(Integer id);
     public  BusinessSchoolDetailsBo queryHomePageDetail1();
}
