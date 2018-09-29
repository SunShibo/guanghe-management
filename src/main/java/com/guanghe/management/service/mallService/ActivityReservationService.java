package com.guanghe.management.service.mallService;

import com.guanghe.management.dao.ActivityReservationDao;
import com.guanghe.management.entity.bo.ActivityReservationBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/27.
 */
@Service("ActivityReservationService")
@Transactional
public class ActivityReservationService  {
    @Resource
    private ActivityReservationDao activityReservationDao;
   public List<ActivityReservationBo> query(Map<String,Object> map){
       return activityReservationDao.query(map);
   }
    public int querycount(){
      return   activityReservationDao.querycount();
    }
    public int queryListcount(Map<String,Object> map){
       return activityReservationDao.queryListcount(map);
    }
    public void updatestatus(ActivityReservationBo activityReservationBo){
        activityReservationDao.updatestatus(activityReservationBo);
    }
    public  ActivityReservationBo queryById(Integer id){
       return activityReservationDao.queryById(id);
    }
}
