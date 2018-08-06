package com.guanghe.management.entity.bo;

import com.guanghe.management.common.base.BaseModel;

import java.util.Date;

/**
 * Created by shishiming on 2018/7/31.
 */
public class PrivateInvestmentTransactionRecordBO extends BaseModel {

    private Integer id; //id
    private Integer privateInvestmentId; //私募投资id
    private String name; //所管理私募基金名称
    private String investmentDirection; //投资方向名称
    private String profit; //收益情况
    private String scale; //基金规模
    private Date createTime; //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrivateInvestmentId() {
        return privateInvestmentId;
    }

    public void setPrivateInvestmentId(Integer privateInvestmentId) {
        this.privateInvestmentId = privateInvestmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvestmentDirection() {
        return investmentDirection;
    }

    public void setInvestmentDirection(String investmentDirection) {
        this.investmentDirection = investmentDirection;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
