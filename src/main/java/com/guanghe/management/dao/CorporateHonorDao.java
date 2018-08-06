package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.CorporateHonorBo;

/**
 * Created by yxw on 2018/7/30.
 */
public interface CorporateHonorDao {
    public CorporateHonorBo queryCorporateHonor(Integer id);

    public CorporateHonorBo queryCorporateHonorDetail();
    public void updateCorporateHonor(CorporateHonorBo corporateHonor);
    public  void addCorporateHonor(CorporateHonorBo corporateHonor);
    public void deleteCorporateHonor(Integer Id);
}
