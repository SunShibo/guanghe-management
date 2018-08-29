package com.guanghe.management.service;

import com.guanghe.management.dao.WealthManagementDetailsDao;
import com.guanghe.management.entity.bo.WealthManagementDetailsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by shishiming on 2018/7/23.
 */
@Service("wealthManagementDetailsService")
@Transactional
public class WealthManagementDetailsService {

    @Resource
    private WealthManagementDetailsDao wealthManagementDetailsDao;


    //修改WealthManagementDetails byId
    public void updateWealthManagement(WealthManagementDetailsBO wealthManagementDetailsBO){
        wealthManagementDetailsDao.updateWealthManagement(wealthManagementDetailsBO);
    }


    //查询WealthManagementDetailsById
    public WealthManagementDetailsBO queryWealthManagementDetails(){
        return wealthManagementDetailsDao.queryWealthManagementDetails();
    }
}
