package com.guanghe.management.service;

import com.guanghe.management.dao.WealthManagementDetailsDao;
import com.guanghe.management.entity.bo.WealthManagementDetailsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/23.
 */
@Service("wealthManagementDetailsService")
@Transactional
public class WealthManagementDetailsService {

    @Resource
    private WealthManagementDetailsDao wealthManagementDetailsDao;


    //添加WealthManagementDetails
    public int addWealthManagement(WealthManagementDetailsBO wealthManagementDetailsBO){
        if (wealthManagementDetailsBO == null){
            return 0;
        }
        return wealthManagementDetailsDao.addWealthManagement(wealthManagementDetailsBO);
    }

    //删除WealthManagementDetails byId
    public void deleteWealthManagementbyId(Integer id){
        if (id == null || id == 0){
            return;
        }
        wealthManagementDetailsDao.deleteWealthManagementbyId(id);
    }

    //修改WealthManagementDetails byId
    public void updateWealthManagementbyId(WealthManagementDetailsBO wealthManagementDetailsBO){
        if (wealthManagementDetailsBO.getId() == null || wealthManagementDetailsBO.getId() == 0){
            return;
        }
        wealthManagementDetailsDao.updateWealthManagementbyId(wealthManagementDetailsBO);
    }

    //查询WealthManagementDetailsList
    public List<WealthManagementDetailsBO> queryWealthManagementDetailsList(Map<String, Object> map){
        if(map == null){
            return null;
        }
        return wealthManagementDetailsDao.queryWealthManagementDetailsList(map);
    }

    //查询WealthManagementDetailsById
    public WealthManagementDetailsBO queryWealthManagementDetailsById(Integer id){
        return wealthManagementDetailsDao.queryWealthManagementDetailsById(id);
    }
}
