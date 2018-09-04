package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.mallBo.MallImageBo;

import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
public interface MallImageDao {
    public List<MallImageBo> queryMallImageInfo();
    public void updateMallImage(MallImageBo mallImageBo);
    public  void addMallImage(MallImageBo mallImageBo);
    public void deleteMallImage(Integer Id);
    public MallImageBo queryMallImage(Integer Id);
}
