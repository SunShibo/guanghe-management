package com.guanghe.management.dao;


import com.guanghe.management.entity.bo.ModuleBo;

import java.util.List;

/**
 * Created by yxw on 2018/8/7.
 */
public interface ModuleDao {
    public ModuleBo queryModule(Integer id);
    public void updateModule(ModuleBo moduleBo);
    public  void addModule(ModuleBo moduleBo);
    public void deleteModule(Integer Id);
    public List<ModuleBo> queryModuleInfo();
}
