package com.guanghe.management.entity.bo;

/**
 * Created by yxw on 2018/9/14.
 */
public class AccountManagementBo {
    private  Integer id;
    private  String phone_number;
    private  Integer integral;
    private Integer leavestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getLeaveStatus() {
        return leavestatus;
    }

    public void setLeaveStatus(Integer leaveStatus) {
        this.leavestatus = leaveStatus;
    }
}
