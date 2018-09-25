package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.CoreTeamBo;
import com.guanghe.management.entity.bo.EmployeeBo;

import java.util.List;

/**
 * Created by yxw on 2018/7/18.
 */
public interface CoreTeamDao {

    public EmployeeBo queryCoreTeam(Integer id);
    public void updateCoreTeam(EmployeeBo coreTeam);
    public  void addCoreTeam(EmployeeBo coreTeam);
    public void deleteCoreTeam(Integer Id);
    public List<EmployeeBo> queryEmployee();
    public  CoreTeamBo queryHomeCoreTeam();

}
