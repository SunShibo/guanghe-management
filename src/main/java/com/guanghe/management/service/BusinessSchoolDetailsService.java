package com.guanghe.management.service;

import com.guanghe.management.dao.BusinessSchoolDetailsDao;
import com.guanghe.management.entity.bo.BusinessSchoolDetailsBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yxw on 2018/7/23.
 */
@Service("BusinessSchoolDetailsService")
@Transactional
public class BusinessSchoolDetailsService {
    @Resource
    private BusinessSchoolDetailsDao businessSchoolDetailsDao;
    public BusinessSchoolDetailsBo queryHomePageDetail(Integer id){
        return  businessSchoolDetailsDao.queryHomePageDetail(id);
    }
    public  void updateHomePageDetail(BusinessSchoolDetailsBo businessSchoolDetailsBo){
        businessSchoolDetailsDao.updateHomePageDetail(businessSchoolDetailsBo);
    }
    public  void addHomePageDetail(BusinessSchoolDetailsBo businessSchoolDetailsBo){
        businessSchoolDetailsDao.addHomePageDetail(businessSchoolDetailsBo);
    }
    public  void deleteHomePageDetail(Integer id){
        businessSchoolDetailsDao.deleteHomePageDetail(id);
    }
    public  BusinessSchoolDetailsBo queryHomePageDetail1(){
        return  businessSchoolDetailsDao.queryHomePageDetail1();
    }
}
