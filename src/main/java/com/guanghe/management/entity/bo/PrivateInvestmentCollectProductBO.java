package com.guanghe.management.entity.bo;

import com.guanghe.management.common.base.BaseModel;

import java.util.Date;

/**
 * Created by shishiming on 2018/7/31.
 */
public class PrivateInvestmentCollectProductBO extends BaseModel {

    private Integer id; //id
    private Integer privateInvestmentId; //私募投资id
    private String name; //采集产品名称
    private Date createTime; //创建时间

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
}
