package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.ClubSystemBo;

import java.util.List;

/**
 * Created by yxw on 2018/9/26.
 */
public interface ClubSystemDao {
    public ClubSystemBo queryClubSystemb(Integer id);
    public void updateClubSystem(ClubSystemBo clubSystemBo);
    public  void addClubSystem(ClubSystemBo clubSystemBo);
    public void deleteClubSystem(Integer Id);
    public List<ClubSystemBo> queryClubSystemBo();
}
