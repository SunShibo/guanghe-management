package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.QueryGoodsListDao;
import com.guanghe.management.entity.mallBo.GoodsListBo;
import com.guanghe.management.entity.mallBo.QueryGoodsResponsBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/30.
 */
@Service("QueryGoodsListService")
@Transactional
public class QueryGoodsListService {
    @Resource
    private QueryGoodsListDao queryGoodsListDao;
    public List<GoodsListBo> QueryGoodsList(Map<String,Object> map){
      return   queryGoodsListDao.QueryGoodsList(map);
    }
    public  int  queryGoodsCount(QueryGoodsResponsBo queryGoodsResponsBo){
        return  queryGoodsListDao.queryGoodsCount(queryGoodsResponsBo);
    }
 }
