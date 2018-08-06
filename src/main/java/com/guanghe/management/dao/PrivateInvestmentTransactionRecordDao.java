package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.PrivateInvestmentTransactionRecordBO;

import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
public interface PrivateInvestmentTransactionRecordDao {

    public int queryTransactionRecordCount(Integer privateInvestmentId);

    public List<PrivateInvestmentTransactionRecordBO> queryTransactionRecordList(Integer privateInvestmentId);

    public PrivateInvestmentTransactionRecordBO queryTransactionRecordById(Integer id);

    public void deleteTransactionRecord(Integer id);

    public void addTransactionRecordBO(PrivateInvestmentTransactionRecordBO bo);

    public void updateTransactionRecordBO(PrivateInvestmentTransactionRecordBO bo);

}
