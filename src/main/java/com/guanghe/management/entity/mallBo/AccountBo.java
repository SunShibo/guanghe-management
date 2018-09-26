package com.guanghe.management.entity.mallBo;

import java.util.Date;

/**
 * Created by yxw on 2018/8/1.
 * 账户信息
 */
public class AccountBo {
    private  Integer id;
    private  Integer userId;
    private  String paymentPassword;//支付密码
    private  Integer integral;//积分余额
    private Date createTime;
    private  Integer leavestatus;

    public Integer getLeavestatus() {
        return leavestatus;
    }

    public void setLeavestatus(Integer leavestatus) {
        this.leavestatus = leavestatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
