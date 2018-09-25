package com.guanghe.management.service;

import com.guanghe.management.dao.CoreTeamDao;
import com.guanghe.management.entity.bo.CoreTeamBo;
import com.guanghe.management.entity.bo.EmployeeBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/7/18.
 */
@Service("CoreTeamService")
@Transactional
public class CoreTeamService {
    @Resource
    private CoreTeamDao coreTeamDao;
    public EmployeeBo queryCoreTeam(Integer id){
        return coreTeamDao.queryCoreTeam(id);
    }
    public  void updateCoreTeam (EmployeeBo coreTeam){

        coreTeamDao.updateCoreTeam(coreTeam);
       }
    public  void deleteCoreTeam(Integer id){

        coreTeamDao.deleteCoreTeam(id);
    }
    public  void addCoreTeam(EmployeeBo coreTeam){
        // 添加数据
        coreTeamDao.addCoreTeam(coreTeam);
    }
    public List<EmployeeBo> queryEmloyee(){
        return  coreTeamDao.queryEmployee();
    }
    public  CoreTeamBo queryHomeCoreTeam(){
        return  coreTeamDao.queryHomeCoreTeam();
    }
}
