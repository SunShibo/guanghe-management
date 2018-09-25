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
    private String productTerm; //产品期限
    private String comparisonDatum; //比较基准
    private Date createTime; //创建时间
    private Date startTime; //开始时间
    private Date endTime; //开始时间
    private Integer amountOfInvestment; //起投金额
    private Integer increasingAmount; //递增金额
    private Integer productScaleStart; //产品规模开始
    private Integer productScaleEnd; //产品规模结束
    private String imgUrl;//详情图片地址

    private Integer investmentPoinId; //投资起点id
    private Integer productTermId; //产品期限id
    private Integer riskLevelId; //风险级别id
    private Integer incomeTypeId; //收益类型id

    private Date recommendTime;//推荐日期
    private  String wapImage;

    public String getWapImage() {
        return wapImage;
    }

    public void setWapImage(String wapImage) {
        this.wapImage = wapImage;
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

    public String getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(String productTerm) {
        this.productTerm = productTerm;
    }

    public String getComparisonDatum() {
        return comparisonDatum;
    }

    public void setComparisonDatum(String comparisonDatum) {
        this.comparisonDatum = comparisonDatum;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }
}
