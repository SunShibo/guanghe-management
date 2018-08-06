package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentBO;

import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentDao {

    public int queryPrivateInvestmentCount(Map<String, Object> map);

    public List<PrivateInvestmentBO> queryPrivateInvestmentList(Map<String, Object> map);

    public PrivateInvestmentBO queryPrivateInvestmentById(Integer id);

    public void deletePrivateInvestment(Integer id);

    public void addPrivateInvestmentBO(PrivateInvestmentBO bo);

    public void updatePrivateInvestmentBO(PrivateInvestmentBO bo);

    public void setRecommendTime(Integer id);

    public List<PrivateInvestmentBO> queryPrivateInvestmentListByRecommendTime();

}
