package com.guanghe.management.service;

import com.guanghe.management.dao.ModuleDao;
import com.guanghe.management.entity.bo.ModuleBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
@Service("ModuleService")
@Transactional
public class ModuleService {
    @Resource
    private ModuleDao moduleDao;
    public ModuleBo queryModule(Integer id){
        return  moduleDao.queryModule(id);
    }
    public void updateModule(ModuleBo moduleBo){
        moduleDao.updateModule(moduleBo);
    }
    public  void addModule(ModuleBo moduleBo){
        moduleDao.addModule(moduleBo);
    }
    public void deleteModule(Integer Id){
        moduleDao.deleteModule(Id);
    }
    public List<ModuleBo> queryModuleInfo(){
        return  moduleDao.queryModuleInfo();
    }
}
