package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.PrivateClubBo;

import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
public interface PrivateClubDao {
    public PrivateClubBo queryPrivateClub(Integer id);
    public void updatePrivateClub(PrivateClubBo privateClubBo);
    public  void addPrivateClub(PrivateClubBo privateClubBo);
    public void deletePrivateClub(Integer Id);
    public List<PrivateClubBo> queryPrivateClubInfo();
}

