package com.guanghe.management.dao.mallDao;



import com.guanghe.management.entity.mallBo.BrandBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
public interface BrandDao {
    public BrandBo queryBrand(Integer id);
    public List<BrandBo> queryBrandInfo(Map<String, Object> map);//通过二级分类id查询品牌
    public void updateBrand(BrandBo brand);
    public  void addBrand(BrandBo brand);
    public void deleteBrand(Integer Id);
    public  List<BrandBo> queryBrandOnclick(Map<String, Object> map);
    public  BrandBo queryBrandByGoodsId(Integer id);
    public  List<BrandBo> queryBranddetail(Map<String, Object> map);

    int getBrandListCount(Map<String, Object> map);

    List<Map<String,Object>> getBrandList(Map<String, Object> map);
    
    void updateById(Map<String, Object> map);

    void insertBrand(Map<String, Object> map);
}
