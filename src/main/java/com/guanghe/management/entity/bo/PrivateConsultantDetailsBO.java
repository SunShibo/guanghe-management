package com.guanghe.management.entity.bo;

import com.guanghe.management.common.base.BaseModel;

import java.util.Date;

/**
 * Created by shishiming on 2018/7/23.
 */
public class PrivateConsultantDetailsBO extends BaseModel{
    private Integer id;//id
    private String name;//名字
    private String gender;//性别
    private String position;//职位
    private String synopsis;//简介
    private String imgUrl;//图片url
    private String createUser;//创建者
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
