package com.guanghe.management.service;

import com.guanghe.management.dao.AdminDao;
import com.guanghe.management.entity.bo.AdminBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by yxw on 2018/7/19.
 */
@Service("AdminService")
@Transactional
public class AdminService {
    @Resource
    private AdminDao userInfoDao;

    public AdminBo queryUserInfoByAccount(String account){
        AdminBo userInfo = userInfoDao.queryUserInfoByAccount(account);
        if(userInfo == null){
            return null;
        }
        userInfo.setUuid(UUID.randomUUID().toString());
        return userInfo;
    }
}
