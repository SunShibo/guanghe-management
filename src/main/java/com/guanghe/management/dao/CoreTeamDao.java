package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.CoreTeamBo;
import com.guanghe.management.entity.bo.EmployeeBo;

import java.util.List;

/**
 * Created by yxw on 2018/7/18.
 */
public interface CoreTeamDao {

    public CoreTeamBo queryCoreTeam(Integer id);
    public void updateCoreTeam(CoreTeamBo coreTeam);
    public  void addCoreTeam(CoreTeamBo coreTeam);
    public void deleteCoreTeam(Integer Id);
    public List<EmployeeBo> queryEmployee();
    public  CoreTeamBo queryHomeCoreTeam();

}
