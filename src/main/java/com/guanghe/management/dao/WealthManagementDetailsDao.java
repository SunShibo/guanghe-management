package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.WealthManagementDetailsBO;

import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/23.
 */
public interface WealthManagementDetailsDao {
    //添加WealthManagementDetails
    int addWealthManagement(WealthManagementDetailsBO wealthManagementDetailsBO);
    //删除WealthManagementDetails byId
    void deleteWealthManagementbyId(Integer id);
    //修改WealthManagementDetails byId
    void updateWealthManagementbyId(WealthManagementDetailsBO wealthManagementDetailsBO);
    //查询WealthManagementDetailsList
    List<WealthManagementDetailsBO> queryWealthManagementDetailsList(Map<String, Object> map);
    //查询WealthManagementDetailsById
    WealthManagementDetailsBO queryWealthManagementDetailsById(Integer id);
}
