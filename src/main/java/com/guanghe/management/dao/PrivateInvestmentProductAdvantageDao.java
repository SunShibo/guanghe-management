package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentProductAdvantageBO;

import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentProductAdvantageDao {

    public int queryProductAdvantageCount(Integer privateInvestmentId);

    public List<PrivateInvestmentProductAdvantageBO> queryProductAdvantageList(Integer privateInvestmentId);

    public PrivateInvestmentProductAdvantageBO queryProductAdvantageById(Integer id);

    public void deleteProductAdvantage(Integer id);

    public void addProductAdvantageBO(PrivateInvestmentProductAdvantageBO bo);

    public void updateProductAdvantageBO(PrivateInvestmentProductAdvantageBO bo);

}
