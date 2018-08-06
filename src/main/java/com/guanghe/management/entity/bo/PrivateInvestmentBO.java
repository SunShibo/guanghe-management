package com.guanghe.management.entity.bo;

import com.guanghe.management.common.base.BaseModel;

import java.util.Date;

/**
 * Created by shishiming on 2018/7/31.
 */
public class PrivateInvestmentBO extends BaseModel {

    private Integer id; //id
    private String fundName; //基金名称
    private String fundType; //基金类型
    private String operations; //运作方式
    private String fundManager; //基金管理人
    private String fundCustodian; //基金托管人
    private String productScale; //产品规模
    private String subscribeStartingPoint; //认购起点
    private String productTerm; //产品期限
    private String fundInvestment; //基金投向
    private String capitalCost; //资金费用
    private String comparisonDatum; //比较基准
    private String performanceReward; //业绩报酬
    private Date createTime; //创建时间
    private Date startTime; //开始时间
    private Date endTime; //开始时间
    private Integer amountOfInvestment; //起投金额
    private Integer increasingAmount; //递增金额
    private Integer productScaleStart; //产品规模开始
    private Integer productScaleEnd; //起投金额结束

    private Integer investmentPoinId; //投资起点id
    private Integer productTermId; //产品期限id
    private Integer riskLevelId; //风险级别id
    private Integer incomeTypeId; //收益类型id

    private Date recommendTime;//推荐日期

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public String getFundCustodian() {
        return fundCustodian;
    }

    public void setFundCustodian(String fundCustodian) {
        this.fundCustodian = fundCustodian;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getSubscribeStartingPoint() {
        return subscribeStartingPoint;
    }

    public void setSubscribeStartingPoint(String subscribeStartingPoint) {
        this.subscribeStartingPoint = subscribeStartingPoint;
    }

    public String getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(String productTerm) {
        this.productTerm = productTerm;
    }

    public String getFundInvestment() {
        return fundInvestment;
    }

    public void setFundInvestment(String fundInvestment) {
        this.fundInvestment = fundInvestment;
    }

    public String getCapitalCost() {
        return capitalCost;
    }

    public void setCapitalCost(String capitalCost) {
        this.capitalCost = capitalCost;
    }

    public String getComparisonDatum() {
        return comparisonDatum;
    }

    public void setComparisonDatum(String comparisonDatum) {
        this.comparisonDatum = comparisonDatum;
    }

    public String getPerformanceReward() {
        return performanceReward;
    }

    public void setPerformanceReward(String performanceReward) {
        this.performanceReward = performanceReward;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAmountOfInvestment() {
        return amountOfInvestment;
    }

    public void setAmountOfInvestment(Integer amountOfInvestment) {
        this.amountOfInvestment = amountOfInvestment;
    }

    public Integer getIncreasingAmount() {
        return increasingAmount;
    }

    public void setIncreasingAmount(Integer increasingAmount) {
        this.increasingAmount = increasingAmount;
    }

    public Integer getProductScaleStart() {
        return productScaleStart;
    }

    public void setProductScaleStart(Integer productScaleStart) {
        this.productScaleStart = productScaleStart;
    }

    public Integer getProductScaleEnd() {
        return productScaleEnd;
    }

    public void setProductScaleEnd(Integer productScaleEnd) {
        this.productScaleEnd = productScaleEnd;
    }

    public Integer getInvestmentPoinId() {
        return investmentPoinId;
    }

    public void setInvestmentPoinId(Integer investmentPoinId) {
        this.investmentPoinId = investmentPoinId;
    }

    public Integer getProductTermId() {
        return productTermId;
    }

    public void setProductTermId(Integer productTermId) {
        this.productTermId = productTermId;
    }

    public Integer getRiskLevelId() {
        return riskLevelId;
    }

    public void setRiskLevelId(Integer riskLevelId) {
        this.riskLevelId = riskLevelId;
    }

    public Integer getIncomeTypeId() {
        return incomeTypeId;
    }

    public void setIncomeTypeId(Integer incomeTypeId) {
        this.incomeTypeId = incomeTypeId;
    }
}
