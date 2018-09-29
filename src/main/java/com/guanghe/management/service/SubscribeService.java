package com.guanghe.management.service;

import com.guanghe.management.dao.SubscribeDao;
import com.guanghe.management.entity.bo.SubscribeBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/29.
 */
@Service("SubscribeService")
@Transactional
public class SubscribeService {
    @Resource
    private SubscribeDao subscribeDao;
    public List<SubscribeBo> query(Map<String,Object> map){
       return     subscribeDao.query(map);
    }
    public   int querycount(){
        return  subscribeDao.querycount();
    }
    public int queryListcount(Map<String,Object> map){
        return  subscribeDao.queryListcount(map);
    }
    public void updatestatus(SubscribeBo subscribeBo){
        subscribeDao.updatestatus(subscribeBo);
    }
    public SubscribeBo queryById(Integer id){
      return   subscribeDao.queryById(id);
    }
}
