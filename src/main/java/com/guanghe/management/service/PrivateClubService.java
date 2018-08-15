package com.guanghe.management.service;
import com.guanghe.management.dao.PrivateClubDao;
import com.guanghe.management.entity.bo.PrivateClubBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
@Service("PrivateClubService")
@Transactional
public class PrivateClubService {
    @Resource
    private PrivateClubDao privateClubDao;
    public PrivateClubBo queryPrivateClub(Integer id){
        return  privateClubDao.queryPrivateClub(id);
    }
    public void updatePrivateClub(PrivateClubBo privateClubBo){
        privateClubDao.updatePrivateClub(privateClubBo);
    }
    public  void addPrivateClub(PrivateClubBo privateClubBo){
        privateClubDao.addPrivateClub(privateClubBo);
    }
    public void deletePrivateClub(Integer Id){
        privateClubDao.deletePrivateClub(Id);
    }
    public List<PrivateClubBo> queryPrivateClubInfo(){
        return  privateClubDao.queryPrivateClubInfo();
    }
}
