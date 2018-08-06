package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentCollectProductBO;

import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentCollectProductDao {

    public int queryCollectProductCount(Integer privateInvestmentId);

    public List<PrivateInvestmentCollectProductBO> queryCollectProductList(Integer privateInvestmentId);

    public PrivateInvestmentCollectProductBO queryCollectProductById(Integer id);

    public void deleteCollectProduct(Integer id);

    public void addCollectProductBO(PrivateInvestmentCollectProductBO bo);

    public void updateCollectProductBO(PrivateInvestmentCollectProductBO bo);

}
