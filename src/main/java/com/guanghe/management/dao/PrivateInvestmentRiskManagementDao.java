package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentRiskManagementBO;

import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentRiskManagementDao {

    public int queryRiskManagementCount(Integer privateInvestmentId);

    public List<PrivateInvestmentRiskManagementBO> queryRiskManagementList(Integer privateInvestmentId);

    public PrivateInvestmentRiskManagementBO queryRiskManagementById(Integer id);

    public void deleteRiskManagement(Integer id);

    public void addRiskManagementBO(PrivateInvestmentRiskManagementBO bo);

    public void updateRiskManagementBO(PrivateInvestmentRiskManagementBO bo);

}
