package com.guanghe.management.service;

import com.guanghe.management.dao.PrivateInvestmentTransactionRecordDao;
import com.guanghe.management.entity.bo.PrivateInvestmentTransactionRecordBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
@Service("privateInvestmentTransactionRecordService")
@Transactional
public class PrivateInvestmentTransactionRecordService {

    @Resource
    private PrivateInvestmentTransactionRecordDao privateInvestmentTransactionRecordDao;

    public int queryPrivateInvestmentTransactionRecordCount(Integer privateInvestmentId){
        return privateInvestmentTransactionRecordDao.queryTransactionRecordCount(privateInvestmentId);
    }

    public List<PrivateInvestmentTransactionRecordBO> queryPrivateInvestmentTransactionRecordList(Integer privateInvestmentId){
        List<PrivateInvestmentTransactionRecordBO> privateInvestmentTransactionRecord = privateInvestmentTransactionRecordDao.queryTransactionRecordList(privateInvestmentId);
        return privateInvestmentTransactionRecord;

    }

    public PrivateInvestmentTransactionRecordBO queryPrivateInvestmentTransactionRecordById(Integer id){
        if (id == null){
            return null;
        }
        PrivateInvestmentTransactionRecordBO bo = privateInvestmentTransactionRecordDao.queryTransactionRecordById(id);
        return bo;
    }

    public void deletePrivateInvestmentTransactionRecord(Integer id){
        if (id == null){
            return ;
        }
        privateInvestmentTransactionRecordDao.deleteTransactionRecord(id);
    }

    public void addPrivateInvestmentTransactionRecord(PrivateInvestmentTransactionRecordBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentTransactionRecordDao.addTransactionRecordBO(bo);
    }

    public void updatePrivateInvestmentTransactionRecordBO(PrivateInvestmentTransactionRecordBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentTransactionRecordDao.updateTransactionRecordBO(bo);
    }

}
