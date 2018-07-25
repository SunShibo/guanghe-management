package com.guanghe.management.service;

import com.guanghe.management.dao.AssetManagementDetailsDao;
import com.guanghe.management.entity.bo.AssetManagementDetailsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by shishiming on 2018/7/23.
 */
@Service("assetManagementDetailsService")
@Transactional
public class AssetManagementDetailsService {

    @Resource
    private AssetManagementDetailsDao assetManagementDetailsDao;


    //添加AssetManagementDetails
    public int addAssetManagement(AssetManagementDetailsBO asset){
        if (asset == null){
            return 0;
        }
        return assetManagementDetailsDao.addAssetManagement(asset);
    }

    //删除AssetManagementDetails byId
    public void deleteAssetManagementbyId(Integer id){
        if (id == null || id == 0){
            return;
        }
        assetManagementDetailsDao.deleteAssetManagementbyId(id);
    }

    //修改AssetManagementDetails byId
    public void updateAssetManagementbyId(AssetManagementDetailsBO asset){
        if (asset.getId() == null || asset.getId() == 0){
            return;
        }
        assetManagementDetailsDao.updateAssetManagementbyId(asset);
    }

    //查询AssetManagementDetailsList
    public List<AssetManagementDetailsBO> queryAssetManagementDetailsList(Map<String, Object> map){
        if(map == null){
            return null;
        }
        return assetManagementDetailsDao.queryAssetManagementDetailsList(map);
    }

    //查询AssetManagementDetailsById
    public AssetManagementDetailsBO queryAssetManagementDetailsById(Integer id){
        return assetManagementDetailsDao.queryAssetManagementDetailsById(id);
    }
}
