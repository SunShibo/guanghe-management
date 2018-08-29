package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.WealthManagementDetailsBO;

/**
 * Created by shishiming on 2018/7/23.
 */
public interface WealthManagementDetailsDao {
    //修改WealthManagementDetails byId
    void updateWealthManagement(WealthManagementDetailsBO wealthManagementDetailsBO);
    //查询WealthManagementDetails
    WealthManagementDetailsBO queryWealthManagementDetails();
}
