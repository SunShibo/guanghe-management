package com.guanghe.management.service;

import com.guanghe.management.dao.ClubSystemDao;
import com.guanghe.management.entity.bo.ClubSystemBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/9/26.
 */
@Service("ClubSystemService")
@Transactional
public class ClubSystemService {
    @Resource
    private ClubSystemDao clubSystemDao;
    public ClubSystemBo queryClubSystemb(Integer id){
        return clubSystemDao.queryClubSystemb(id);
    }
    public void updateClubSystem(ClubSystemBo clubSystemBo){
        clubSystemDao.updateClubSystem(clubSystemBo);
    }
    public  void addClubSystem(ClubSystemBo clubSystemBo){
        clubSystemDao.addClubSystem(clubSystemBo);
    }
    public void deleteClubSystem(Integer Id){
        clubSystemDao.deleteClubSystem(Id);
    }
    public List<ClubSystemBo> queryClubSystemBo(){
        return clubSystemDao.queryClubSystemBo();
    }
}
