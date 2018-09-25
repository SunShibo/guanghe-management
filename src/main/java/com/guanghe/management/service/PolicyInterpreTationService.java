package com.guanghe.management.service;

import com.guanghe.management.dao.PolicyInterpreTationDao;
import com.guanghe.management.entity.bo.PolicyInterpreTationBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
@Service("PolicyInterpreTationService")
@Transactional
public class PolicyInterpreTationService {
    @Resource
    private PolicyInterpreTationDao policyInterpreTationDao;
    public int queryPolicyInterpreTationCount(Map<String, Object> map){
      return   policyInterpreTationDao.queryPolicyInterpreTationCount(map);
    }

    public List<PolicyInterpreTationBo> queryPolicyInterpreTationList(Map<String, Object> map){
        return policyInterpreTationDao.queryPolicyInterpreTationList(map);
    }

    public PolicyInterpreTationBo queryPolicyInterpreTationById(Integer newId){
        return  policyInterpreTationDao.queryPolicyInterpreTationById(newId);
    }

    public void deletePolicyInterpreTation(Integer newId){
        policyInterpreTationDao.deletePolicyInterpreTation(newId);
    }

    public void addPolicyInterpreTation(PolicyInterpreTationBo news){
        policyInterpreTationDao.addPolicyInterpreTation(news);
    }

    public void updatePolicyInterpreTation(PolicyInterpreTationBo news){
        policyInterpreTationDao.updatePolicyInterpreTation(news);
    }


}
