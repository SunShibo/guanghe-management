package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.ExpertIectureHallBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/7/31.
 */
public interface ExpertIectureHallDao {
    public int queryExpertIectureHallCount(Map<String, Object> map);

    public List<ExpertIectureHallBo> queryExpertIectureHallList(Map<String, Object> map);

    public ExpertIectureHallBo queryExpertIectureHallById(Integer newId);

    public void deleteExpertIectureHall(Integer newId);

    public void addExpertIectureHall(ExpertIectureHallBo news);

    public void updateExpertIectureHall(ExpertIectureHallBo news);
}
