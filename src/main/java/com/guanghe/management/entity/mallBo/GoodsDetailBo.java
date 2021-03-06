package com.guanghe.management.entity.mallBo;

import java.util.Date;

/**
 * Created by yxw on 2018/8/20.
 */
public class GoodsDetailBo {
    private  Integer id;
    private  String name;
    private  double weight;
    private  String introduceImgUrl;
    private  Integer state;//下架/无货
    private  Integer brandId;
    private  Integer goodsTypeId;
    private  String createUser;
    private  String updateUser;
    private  Date createTime;
    private  Date updateTime;
    private  Integer followCount;
    private  Integer saleCount;
    private  Integer homeState;//0 正常  1 推荐商品 2 新商品上架
    private  Integer leaveId;
    private  Integer price;
    private  Integer  goodsId;
    private  String specification;
    private  Date preferentialStartTime;
    private Date preferentialEndTime;
    private  Integer sku;
    private  Integer preferentialPrice;
    private  String  status;
    private  Integer number;
    private  Integer carId;
    private  Integer stock;


    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Date getPreferentialStartTime() {
        return preferentialStartTime;
    }

    public void setPreferentialStartTime(Date preferentialStartTime) {
        this.preferentialStartTime = preferentialStartTime;
    }

    public Date getPreferentialEndTime() {
        return preferentialEndTime;
    }

    public void setPreferentialEndTime(Date preferentialEndTime) {
        this.preferentialEndTime = preferentialEndTime;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(Integer preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getIntroduceImgUrl() {
        return introduceImgUrl;
    }

    public void setIntroduceImgUrl(String introduceImgUrl) {
        this.introduceImgUrl = introduceImgUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getHomeState() {
        return homeState;
    }

    public void setHomeState(Integer homeState) {
        this.homeState = homeState;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }
}
