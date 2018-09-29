package com.guanghe.management.entity.bo;

import java.util.Date;

/**
 * Created by yxw on 2018/9/27.
 */
public class Bindingbo {
    private Integer id;//主键
    private Integer userId;//用户id
    private Integer privateConsultantId;//顾问id
    private Date crateTime;//创建时间
    private  String phone;
    private  String phoneNumber;
    private String name;
    private  String jobNumber;
    private  Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getPrivateConsultantId() {
        return privateConsultantId;
    }

    public void setPrivateConsultantId(Integer privateConsultantId) {
        this.privateConsultantId = privateConsultantId;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
}
