package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentInvestmentPoinBO;

import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentInvestmentPoinDao {

    public int queryInvestmentPoinCount();

    public List<PrivateInvestmentInvestmentPoinBO> queryInvestmentPoinList();

    public PrivateInvestmentInvestmentPoinBO queryInvestmentPoinById(Integer id);

    public void deleteInvestmentPoin(Integer id);

    public void addInvestmentPoinBO(String name);

    public void updateInvestmentPoinBO(PrivateInvestmentInvestmentPoinBO bo);

}
