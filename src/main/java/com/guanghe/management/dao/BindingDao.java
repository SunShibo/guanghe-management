package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.ActivityReservationBo;
import com.guanghe.management.entity.bo.Bindingbo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/27.
 */
public interface BindingDao {
    List<Bindingbo> query(Map<String,Object> map);
    int querycount();
    int queryListcount(Map<String,Object> map);
    void updatestatus(Bindingbo bindingbo);
    Bindingbo queryById(Integer id);
}
