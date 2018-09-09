package com.guanghe.management.entity.mallBo;

import java.util.Date;

/**
 * Created by yxw on 2018/9/4.
 */
public class GoodsSpeciFication {
    private Integer sku;
    private  Integer stock;
    private  Integer goodsId;
    private  Integer price;
    private  Integer preferentialPrice;
    private  Date preferentialEndTime;
    private  Date preferentialStartTime;
    private  String specification;
    private  Integer state;//下架/无货

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(Integer preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public Date getPreferentialEndTime() {
        return preferentialEndTime;
    }

    public void setPreferentialEndTime(Date preferentialEndTime) {
        this.preferentialEndTime = preferentialEndTime;
    }

    public Date getPreferentialStartTime() {
        return preferentialStartTime;
    }

    public void setPreferentialStartTime(Date preferentialStartTime) {
        this.preferentialStartTime = preferentialStartTime;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
