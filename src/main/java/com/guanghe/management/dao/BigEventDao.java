package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.BigEventBo;

import java.util.List;

/**
 * Created by yxw on 2018/7/18.
 */
public interface BigEventDao {
    public BigEventBo queryBigEvent(Integer id);

    public List<BigEventBo> queryBigEventDetail();
    public void updateBigEvent(BigEventBo bigEvent);
    public  void addBigEvent(BigEventBo bigEvent);
    public void deleteBigEvent(Integer Id);
}
