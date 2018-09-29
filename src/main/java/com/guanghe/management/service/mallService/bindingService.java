package com.guanghe.management.service.mallService;

import com.guanghe.management.dao.BindingDao;
import com.guanghe.management.entity.bo.Bindingbo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/27.
 */
@Service("bindingService")
@Transactional
public class bindingService {
    @Resource
    private BindingDao bindingDao;
    public  List<Bindingbo> query(Map<String,Object> map){
        return bindingDao.query(map);
    }
    public  int querycount(){
        return  bindingDao.querycount();
    }
    public int queryListcount(Map<String,Object> map){
        return  bindingDao.queryListcount(map);
    }
    public  void updatestatus(Bindingbo bindingbo){
        bindingDao.updatestatus(bindingbo);
    }
    public  Bindingbo queryById(Integer id){
      return bindingDao.queryById(id);
    }
}
