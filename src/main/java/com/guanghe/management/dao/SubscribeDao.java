package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.SubscribeBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/29.
 */
public interface SubscribeDao {
    List<SubscribeBo> query(Map<String,Object> map);
    int querycount();
    int queryListcount(Map<String,Object> map);
    void updatestatus(SubscribeBo subscribeBo);
    SubscribeBo queryById(Integer id);
}
