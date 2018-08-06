package com.guanghe.management.service;

import com.guanghe.management.dao.PrivateInvestmentDao;
import com.guanghe.management.entity.bo.PrivateInvestmentBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/18.
 */
@Service("privateInvestmentService")
@Transactional
public class PrivateInvestmentService {

    @Resource
    private PrivateInvestmentDao privateInvestmentDao;

    public int queryPrivateInvestmentCount(Map<String,Object> map){
        return privateInvestmentDao.queryPrivateInvestmentCount(map);
    }

    public List<PrivateInvestmentBO> queryPrivateInvestmentList(Map<String,Object> map){
        List<PrivateInvestmentBO> privateInvestment = privateInvestmentDao.queryPrivateInvestmentList(map);
        return privateInvestment;

    }

    public PrivateInvestmentBO queryPrivateInvestmentById(Integer id){
        if (id == null){
            return null;
        }
        PrivateInvestmentBO bo = privateInvestmentDao.queryPrivateInvestmentById(id);
        return bo;
    }

    public void deletePrivateInvestment(Integer id){
        if (id == null){
            return ;
        }
        privateInvestmentDao.deletePrivateInvestment(id);
    }

    public void addPrivateInvestment(PrivateInvestmentBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentDao.addPrivateInvestmentBO(bo);
    }

    public void updatePrivateInvestmentBO(PrivateInvestmentBO bo){
        if (bo == null){
            return ;
        }
        privateInvestmentDao.updatePrivateInvestmentBO(bo);
    }

    public void setRecommendTime(Integer id){
        if (id == null){
            return ;
        }
        privateInvestmentDao.setRecommendTime(id);
    }

    public List<PrivateInvestmentBO> queryPrivateInvestmentListByRecommendTime(){
        return privateInvestmentDao.queryPrivateInvestmentListByRecommendTime();
    }

}
