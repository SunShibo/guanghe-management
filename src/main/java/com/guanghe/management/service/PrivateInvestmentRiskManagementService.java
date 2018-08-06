package com.guanghe.management.service;

import com.guanghe.management.dao.PrivateInvestmentRiskManagementDao;
import com.guanghe.management.entity.bo.PrivateInvestmentRiskManagementBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
@Service("privateInvestmentRiskManagementService")
@Transactional
public class PrivateInvestmentRiskManagementService {

    @Resource
    private PrivateInvestmentRiskManagementDao privateInvestmentRiskManagementDao;

    public int queryPrivateInvestmentRiskManagementCount(Integer privateInvestmentId){
        return privateInvestmentRiskManagementDao.queryRiskManagementCount(privateInvestmentId);
    }

    public List<PrivateInvestmentRiskManagementBO> queryPrivateInvestmentRiskManagementList(Integer privateInvestmentId){
        List<PrivateInvestmentRiskManagementBO> privateInvestmentRiskManagement = privateInvestmentRiskManagementDao.queryRiskManagementList(privateInvestmentId);
        return privateInvestmentRiskManagement;

    }

    public PrivateInvestmentRiskManagementBO queryPrivateInvestmentRiskManagementById(Integer id){
        if (id == null){
            return null;
        }
        PrivateInvestmentRiskManagementBO bo = privateInvestmentRiskManagementDao.queryRiskManagementById(id);
        return bo;
    }

    public void deletePrivateInvestmentRiskManagement(Integer id){
        if (id == null){
            return ;
        }
        privateInvestmentRiskManagementDao.deleteRiskManagement(id);
    }

    public void addPrivateInvestmentRiskManagement(PrivateInvestmentRiskManagementBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentRiskManagementDao.addRiskManagementBO(bo);
    }

    public void updatePrivateInvestmentRiskManagementBO(PrivateInvestmentRiskManagementBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentRiskManagementDao.updateRiskManagementBO(bo);
    }

}
