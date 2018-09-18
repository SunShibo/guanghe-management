package com.guanghe.management.entity.mallBo;

import com.guanghe.management.common.base.BaseModel;

import java.util.Date;

/**
 * Created by yxw on 2018/8/1.
 */
public class IntegralTransactionBo extends BaseModel{
    private  Integer id;
    private  Integer userId;
    private  Long orderId;
    private  Integer deal;//交易金额
    private  Integer oddintegral;
    private  Integer state;//交易状态
    private  Date createTime;
    private String payinfo;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Integer getOddintegral() {
        return oddintegral;
    }

    public void setOddintegral(Integer oddintegral) {
        this.oddintegral = oddintegral;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo;
    }
}
