package com.guanghe.management.service;

import com.guanghe.management.dao.AssetManagementDetailsDao;
import com.guanghe.management.entity.bo.AssetManagementDetailsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by shishiming on 2018/7/23.
 */
@Service("assetManagementDetailsService")
@Transactional
public class AssetManagementDetailsService {

    @Resource
    private AssetManagementDetailsDao assetManagementDetailsDao;

    //修改AssetManagementDetails
    public void updateAssetManagement(AssetManagementDetailsBO asset){
        assetManagementDetailsDao.updateAssetManagement(asset);
    }


    //查询AssetManagementDetails
    public AssetManagementDetailsBO queryAssetManagementDetails(){
        return assetManagementDetailsDao.queryAssetManagementDetails();
    }
}
