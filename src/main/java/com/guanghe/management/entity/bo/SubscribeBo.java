package com.guanghe.management.entity.bo;

import java.util.Date;

/**
 * Created by yxw on 2018/9/29.
 */
public class SubscribeBo {
    private Integer id;//id
    private Integer userId;//userId
    private Integer privateInvestmentId;//产品主键
    private String name;//预约人姓名
    private String phone;//产品主键
    private Integer confirmationOfAmount;//确认金额
    private Integer confirmationOfCount;//确认份数
    private Date confirmationOfDate;//确认时间
    private Date create_time;//创建时间
    private Date update_time;//修改时间
    private Integer status;//状态 0，申请预约
    private Integer amountOfInvestment;//用户 预约金额
    private  Date payTime; //购买时间
    private String aveNet; //平均净值
    private  double payCount;// 购买份额
    private  Date Maturity;//到期日
    private  String fundName;
    private Long counton;//投资中总资产
    private Long collected;//待收
    private Long cumulative;//累计投资
    private  Long received;//已收
    private  Long earn;//已赚
   private String phoneNumber;
  private  String productType;
    private String nowNet;//现单位净值

    public String getNowNet() {
        return nowNet;
    }

    public void setNowNet(String nowNet) {
        this.nowNet = nowNet;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCounton() {
        return counton;
    }

    public void setCounton(Long counton) {
        this.counton = counton;
    }

    public Long getCollected() {
        return collected;
    }

    public void setCollected(Long collected) {
        this.collected = collected;
    }

    public Long getCumulative() {
        return cumulative;
    }

    public void setCumulative(Long cumulative) {
        this.cumulative = cumulative;
    }

    public Long getReceived() {
        return received;
    }

    public void setReceived(Long received) {
        this.received = received;
    }

    public Long getEarn() {
        return earn;
    }

    public void setEarn(Long earn) {
        this.earn = earn;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getConfirmationOfAmount() {
        return confirmationOfAmount;
    }

    public void setConfirmationOfAmount(Integer confirmationOfAmount) {
        this.confirmationOfAmount = confirmationOfAmount;
    }

    public Integer getConfirmationOfCount() {
        return confirmationOfCount;
    }

    public void setConfirmationOfCount(Integer confirmationOfCount) {
        this.confirmationOfCount = confirmationOfCount;
    }

    public Date getConfirmationOfDate() {
        return confirmationOfDate;
    }

    public void setConfirmationOfDate(Date confirmationOfDate) {
        this.confirmationOfDate = confirmationOfDate;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmountOfInvestment() {
        return amountOfInvestment;
    }

    public void setAmountOfInvestment(Integer amountOfInvestment) {
        this.amountOfInvestment = amountOfInvestment;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getAveNet() {
        return aveNet;
    }

    public void setAveNet(String aveNet) {
        this.aveNet = aveNet;
    }

    public double getPayCount() {
        return payCount;
    }

    public void setPayCount(double payCount) {
        this.payCount = payCount;
    }

    public Date getMaturity() {
        return Maturity;
    }

    public void setMaturity(Date maturity) {
        Maturity = maturity;
    }
}
