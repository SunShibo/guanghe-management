package com.guanghe.management.service.mallService;

import com.guanghe.management.dao.mallDao.GoodsSpeciFicationDao;
import com.guanghe.management.entity.mallBo.GoodsSpeciFication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/9/5.
 */
@Service("GoodsSpecificationService")
@Transactional
public class GoodsSpecificationService {
    @Resource
    private GoodsSpeciFicationDao goodsSpeciFicationDao;
    public void deleteGoodsSpeciFication(Integer Id){
        goodsSpeciFicationDao.deleteGoodsSpeciFication(Id);
    }
    public List<GoodsSpeciFication> queryGoodsSpeciFication(Integer id){
        return  goodsSpeciFicationDao.queryGoodsSpeciFication(id);
    }
    public GoodsSpeciFication queryGoodsSpeciFicationById(Integer id){
        return goodsSpeciFicationDao.queryGoodsSpeciFicationById(id);
    }
    public  void updateGoodsSpeciFication(GoodsSpeciFication goodsSpeciFication){
         goodsSpeciFicationDao.updateGoodsSpeciFication(goodsSpeciFication);
    }
    public  GoodsSpeciFication addSpeciFication(GoodsSpeciFication goodsSpeciFication){
        return  goodsSpeciFicationDao.addSpeciFication(goodsSpeciFication);
    }
    public void addGoodsSpeciFication(Map<String,Object> map){
        goodsSpeciFicationDao.addGoodsSpeciFication(map);
    }
}
