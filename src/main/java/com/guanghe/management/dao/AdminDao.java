package com.guanghe.management.dao;

import com.guanghe.management.entity.bo.AdminBo;

/**
 * Created by yxw on 2018/7/19.
 */
public interface AdminDao {
    public AdminBo queryUserInfoByAccount(String account);
}
