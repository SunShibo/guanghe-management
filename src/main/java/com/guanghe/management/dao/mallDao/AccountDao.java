package com.guanghe.management.dao.mallDao;


import com.guanghe.management.entity.bo.AccountManagementBo;
import com.guanghe.management.entity.mallBo.AccountBo;

import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
public interface AccountDao {
    public AccountBo queryAccountByUserId(Integer userId);//通过账户Id查询个人信息
    public AccountBo updatePassWord(AccountBo accountBo);//修改支付密码
    public void updateIntegral(AccountBo accountBo);//修改积分
    public void updateAddIntegral(AccountBo accountBo);//管理员增加积分
    public void addAccount(AccountBo accountBo);//添加
    public  void deleteAccount(Integer id);
    public List<AccountManagementBo> queryList(Map<String,Object>map);
    public int queryCount(Map<String,Object>map);
    public void updateAccount(AccountBo accountBo);
}
