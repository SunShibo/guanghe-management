package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.mallBo.GoodTypeBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/1.
 */
public interface GoodsTypeDao {
    public GoodTypeBo queryGoodTypeById(Integer id);//查询目录
    public List<GoodTypeBo> queryGoodType();//通过pid 查一级目录
    public  void updateGoodType(GoodTypeBo goodTypeBo);
    public  void addGoodType(GoodTypeBo GoodTypeBo);
    public void deleteGoodType(Integer Id);
    public  GoodTypeBo queryGoodTypeFirstById(Integer id);
    public  GoodTypeBo queryGoodTypeSecondById(Integer id);

    public  List<GoodTypeBo> queryTypeById();

    public  List<GoodTypeBo> queryGoodTypeByPid(Integer pid); //一级查二级


    int getGoodsTypeListCount(Map<String, Object> map);

    List<Map<String,Object>> getGoodsTypeList(Map<String, Object> map);
    
    void updateById(Map<String, Object> map);

    void addOneGoodsType(Map<String, Object> map);
    
    List<Map<String,Object>> getOneTypeList();
    
    void updateTwoTypeById(Map<String, Object> map);

    void addOneType(Map<String, Object> map);
    
    int getMaxSortByPid(int pid);
    
    void addTwoType(Map<String, Object> map);
}
