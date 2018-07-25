package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.BigEventBo;

/**
 * Created by yxw on 2018/7/18.
 */
public interface BigEventDao {
    public BigEventBo queryBigEvent(Integer id);

    public BigEventBo queryBigEventDetail();
    public void updateBigEvent(BigEventBo bigEvent);
    public  void addBigEvent(BigEventBo bigEvent);
    public void deleteBigEvent(Integer Id);
}
