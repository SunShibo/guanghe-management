package com.guanghe.management.service.mallService;


import com.guanghe.management.dao.mallDao.AccountDao;
import com.guanghe.management.entity.bo.AccountManagementBo;
import com.guanghe.management.entity.mallBo.AccountBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yxw on 2018/8/2.
 */
@Service("AccountService")
@Transactional
public class AccountService {
    @Resource
    private AccountDao accountDao;
    //通过账户Id查询个人信息
    public AccountBo queryAccountByUserId(Integer userId){
        return  accountDao.queryAccountByUserId(userId);
    }
    //修改支付密码
    public AccountBo updatePassWord(AccountBo accountBo){
        return  accountDao.updatePassWord(accountBo);
    }
    //修改积分
    public void updateIntegral(AccountBo accountBo){
        accountDao.updateIntegral(accountBo);
    }
    //管理员增加积分
    public void updateAddIntegral(AccountBo accountBo){
        accountDao.updateAddIntegral(accountBo);
    }
    //添加
    public void addAccount(AccountBo accountBo){
        accountDao.addAccount(accountBo);
    }

    public  void deleteAccount(Integer id){
        accountDao.deleteAccount(id);
    }

    public List<AccountManagementBo> queryList(Map<String,Object> map){
        return  accountDao.queryList(map);
    }
    public int queryCount(Map<String,Object>map){
        return accountDao.queryCount(map);
    }
    public void updateAccount(AccountBo accountBo){
        accountDao.updateAccount(accountBo);
    }
}
