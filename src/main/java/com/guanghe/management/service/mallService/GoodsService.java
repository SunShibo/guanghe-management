package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.GoodsDao;
import com.guanghe.management.dao.mallDao.GoodsImageDao;
import com.guanghe.management.dao.mallDao.GoodsSpeciFicationDao;
import com.guanghe.management.entity.dto.ResultDTOBuilder;
import com.guanghe.management.entity.mallBo.*;
import com.guanghe.management.util.DateUtils;
import com.guanghe.management.util.JsonUtils;
import com.guanghe.management.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Service("GoodsService")
@Transactional
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsImageDao goodsImageDao;
    @Resource
    private GoodsSpeciFicationDao goodsSpeciFicationDao;

    public void updateGoods(GoodsBo goodsBo){
        goodsDao.updateGoods(goodsBo);
    }
    public int queryGoodsCount(GoodsResponseBo goodsResponseBo){
        return goodsDao.queryGoodsCount(goodsResponseBo);
    }
    public  GoodsBo queryGoodsById(Integer id){
        return  goodsDao.queryGoodsById(id);
    }
    public void addGoods(GoodsBo goodsBo){
        goodsDao.addGoods(goodsBo);
    }
    public void deleteGoods(Integer Id){
        goodsDao.deleteGoods(Id);
    }
    public  List<GoodsBo>queryGoods(Map<String,Object>map){
        return goodsDao.queryGoods(map);
    }
    public List<GoodsListBo> queryGoodsInfoSort(Map<String,Object> map){
        return goodsDao.queryGoodsInfoSort(map);
    }
    public List<GoodsBo> queryHomeGoodsList(){
        return  goodsDao.queryHomeGoodsList();
    }
    public  List<GoodsDetailBo> queryGoodsdetail(Integer id){
        return  goodsDao.queryGoodsdetail(id);
    }
    public  List<GoodsListBo> queryGoodsbrandsDetailList(Integer id){
        return  goodsDao.queryGoodsbrandsDetailList(id);
    }

    public String addGoods(String goodsInfo){
        if (StringUtils.isEmpty(goodsInfo)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        }
        GoodsAddRequest goods = (GoodsAddRequest)JsonUtils.getObject4JsonString(goodsInfo, GoodsAddRequest.class,
                DateUtils.DATE_PATTERN);
        if(goods == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        }
        // 判断详情图、规格是否为空
        if(goods.getGoodsImg() == null || goods.getGoodsSpeciFication() == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        }
        GoodsBo goodsBo =new GoodsBo();
        goodsBo.setGoodsTypeId(goods.getGoodsTypeId());
        goodsBo.setIntroduceImgUrl(goods.getIntroduceImgUrl());
        goodsBo.setName(goods.getName());
        goodsBo.setBrandId(goods.getBrandId());
        goodsBo.setWeight(goods.getWeight());
        goodsBo.setLeaveId(goods.getLeaveId());
        goodsDao.addGoods(goodsBo);
        Integer goodsId = goodsBo.getId();
        // 获取详情图信息
        List<GoodsImg> lstImage = JsonUtils.getList4JsonArray(goods.getGoodsImg(), GoodsImg.class);
        for (GoodsImg image : lstImage){
            image.setGoodsId(goodsId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lstImage", lstImage);
        goodsImageDao.batchAddGoodsImg(map);

        // 获取规格信息
        List<GoodsSpeciFication> lstSpeciFication = JsonUtils.getList4JsonArray(goods.getGoodsSpeciFication(),
                GoodsSpeciFication.class);
        for (GoodsSpeciFication speciFication:lstSpeciFication){
            speciFication.setGoodsId(goodsId);
            speciFication.setState(1);
        }
        map.put("speciFication",lstSpeciFication);
        goodsSpeciFicationDao.addGoodsSpeciFication(map);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
    }
    public  int updateGoodsCount(Map<String,Object>map){
       return goodsDao.updateGoodsCount(map);
    }
}
