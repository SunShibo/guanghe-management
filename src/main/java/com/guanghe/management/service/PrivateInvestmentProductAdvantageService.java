package com.guanghe.management.service;

import com.guanghe.management.dao.PrivateInvestmentProductAdvantageDao;
import com.guanghe.management.entity.bo.PrivateInvestmentProductAdvantageBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shishiming on 2018/7/18.
 */
@Service("privateInvestmentProductAdvantageService")
@Transactional
public class PrivateInvestmentProductAdvantageService {

    @Resource
    private PrivateInvestmentProductAdvantageDao privateInvestmentProductAdvantageDao;

    public int queryPrivateInvestmentProductAdvantageCount(Integer privateInvestmentId){
        return privateInvestmentProductAdvantageDao.queryProductAdvantageCount(privateInvestmentId);
    }

    public List<PrivateInvestmentProductAdvantageBO> queryPrivateInvestmentProductAdvantageList(Integer privateInvestmentId){
        List<PrivateInvestmentProductAdvantageBO> privateInvestmentProductAdvantage = privateInvestmentProductAdvantageDao.queryProductAdvantageList(privateInvestmentId);
        return privateInvestmentProductAdvantage;

    }

    public PrivateInvestmentProductAdvantageBO queryPrivateInvestmentProductAdvantageById(Integer id){
        if (id == null){
            return null;
        }
        PrivateInvestmentProductAdvantageBO bo = privateInvestmentProductAdvantageDao.queryProductAdvantageById(id);
        return bo;
    }

    public void deletePrivateInvestmentProductAdvantage(Integer id){
        if (id == null){
            return ;
        }
        privateInvestmentProductAdvantageDao.deleteProductAdvantage(id);
    }

    public void addPrivateInvestmentProductAdvantage(PrivateInvestmentProductAdvantageBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentProductAdvantageDao.addProductAdvantageBO(bo);
    }

    public void updatePrivateInvestmentProductAdvantageBO(PrivateInvestmentProductAdvantageBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentProductAdvantageDao.updateProductAdvantageBO(bo);
    }

}
