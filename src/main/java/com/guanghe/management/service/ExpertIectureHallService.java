package com.guanghe.management.service;

import com.guanghe.management.dao.ExpertIectureHallDao;
import com.guanghe.management.entity.bo.ExpertIectureHallBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
@Service("ExpertIectureHallService")
@Transactional
public class ExpertIectureHallService {
    @Resource
    private ExpertIectureHallDao expertIectureHallDao;
    public int queryExpertIectureHallCount(Map<String, Object> map){
        return  expertIectureHallDao.queryExpertIectureHallCount(map);
    }

    public List<ExpertIectureHallBo> queryExpertIectureHallList(Map<String, Object> map){
        return  expertIectureHallDao.queryExpertIectureHallList(map);
    }

    public ExpertIectureHallBo queryExpertIectureHallById(Integer newId){
        return  expertIectureHallDao.queryExpertIectureHallById(newId);
    }

    public void deleteExpertIectureHall(Integer newId){
        expertIectureHallDao.deleteExpertIectureHall(newId);
    }

    public void addExpertIectureHall(ExpertIectureHallBo news){
        expertIectureHallDao.addExpertIectureHall(news);
    }

    public void updateExpertIectureHall(ExpertIectureHallBo news){
        expertIectureHallDao.updateExpertIectureHall(news);
    }
}
