package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.ActivityReservationBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/27.
 */
public interface ActivityReservationDao {
    List<ActivityReservationBo> query(Map<String,Object> map);
    int querycount();
    int queryListcount(Map<String,Object> map);
    void updatestatus(ActivityReservationBo activityReservationBo);
    ActivityReservationBo queryById(Integer id);

}
