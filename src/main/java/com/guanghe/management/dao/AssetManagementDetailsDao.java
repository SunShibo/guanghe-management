package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.AssetManagementDetailsBO;

/**
 * Created by shishiming on 2018/7/23.
 */
public interface AssetManagementDetailsDao {
    //修改AssetManagementDetails
    void updateAssetManagement(AssetManagementDetailsBO assetManagementDetailsBO);

    //查询AssetManagementDetailsById
    AssetManagementDetailsBO queryAssetManagementDetails();
}
