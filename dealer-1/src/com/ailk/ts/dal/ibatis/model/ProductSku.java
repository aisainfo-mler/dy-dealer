package com.ailk.ts.dal.ibatis.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductSku {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SKUID
     *
     * @mbggenerated
     */
    private Integer skuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.PRODUCT_ID
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SKU_NAME
     *
     * @mbggenerated
     */
    private String skuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SKU_SUB_NAME
     *
     * @mbggenerated
     */
    private String skuSubName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.NORMAL_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal normalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SAIL_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal sailPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.AGENT_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal agentPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.WEIGHT
     *
     * @mbggenerated
     */
    private Integer weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SERVICE_MONTH
     *
     * @mbggenerated
     */
    private Integer serviceMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.STATUS
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.SPE_TYPE
     *
     * @mbggenerated
     */
    private String speType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.CREATER_ID
     *
     * @mbggenerated
     */
    private Integer createrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.CREATE_TIME
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.MODIFIER_ID
     *
     * @mbggenerated
     */
    private Integer modifierId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.MODIFY_TIME
     *
     * @mbggenerated
     */
    private Timestamp modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.IS_RANGEPHONE
     *
     * @mbggenerated
     */
    private String isRangephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.RANGE_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal rangePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.COMMENTS
     *
     * @mbggenerated
     */
    private String comments;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.LISTPIC
     *
     * @mbggenerated
     */
    private Long listpic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.GOOD_TYPE
     *
     * @mbggenerated
     */
    private String goodType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_sku.BSS_GOOD_ID
     *
     * @mbggenerated
     */
    private String bssGoodId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SKUID
     *
     * @return the value of product_sku.SKUID
     *
     * @mbggenerated
     */
    public Integer getSkuid() {
        return skuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SKUID
     *
     * @param skuid the value for product_sku.SKUID
     *
     * @mbggenerated
     */
    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.PRODUCT_ID
     *
     * @return the value of product_sku.PRODUCT_ID
     *
     * @mbggenerated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.PRODUCT_ID
     *
     * @param productId the value for product_sku.PRODUCT_ID
     *
     * @mbggenerated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SKU_NAME
     *
     * @return the value of product_sku.SKU_NAME
     *
     * @mbggenerated
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SKU_NAME
     *
     * @param skuName the value for product_sku.SKU_NAME
     *
     * @mbggenerated
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SKU_SUB_NAME
     *
     * @return the value of product_sku.SKU_SUB_NAME
     *
     * @mbggenerated
     */
    public String getSkuSubName() {
        return skuSubName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SKU_SUB_NAME
     *
     * @param skuSubName the value for product_sku.SKU_SUB_NAME
     *
     * @mbggenerated
     */
    public void setSkuSubName(String skuSubName) {
        this.skuSubName = skuSubName == null ? null : skuSubName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.NORMAL_PRICE
     *
     * @return the value of product_sku.NORMAL_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.NORMAL_PRICE
     *
     * @param normalPrice the value for product_sku.NORMAL_PRICE
     *
     * @mbggenerated
     */
    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SAIL_PRICE
     *
     * @return the value of product_sku.SAIL_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getSailPrice() {
        return sailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SAIL_PRICE
     *
     * @param sailPrice the value for product_sku.SAIL_PRICE
     *
     * @mbggenerated
     */
    public void setSailPrice(BigDecimal sailPrice) {
        this.sailPrice = sailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.AGENT_PRICE
     *
     * @return the value of product_sku.AGENT_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.AGENT_PRICE
     *
     * @param agentPrice the value for product_sku.AGENT_PRICE
     *
     * @mbggenerated
     */
    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.WEIGHT
     *
     * @return the value of product_sku.WEIGHT
     *
     * @mbggenerated
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.WEIGHT
     *
     * @param weight the value for product_sku.WEIGHT
     *
     * @mbggenerated
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SERVICE_MONTH
     *
     * @return the value of product_sku.SERVICE_MONTH
     *
     * @mbggenerated
     */
    public Integer getServiceMonth() {
        return serviceMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SERVICE_MONTH
     *
     * @param serviceMonth the value for product_sku.SERVICE_MONTH
     *
     * @mbggenerated
     */
    public void setServiceMonth(Integer serviceMonth) {
        this.serviceMonth = serviceMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.STATUS
     *
     * @return the value of product_sku.STATUS
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.STATUS
     *
     * @param status the value for product_sku.STATUS
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.SPE_TYPE
     *
     * @return the value of product_sku.SPE_TYPE
     *
     * @mbggenerated
     */
    public String getSpeType() {
        return speType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.SPE_TYPE
     *
     * @param speType the value for product_sku.SPE_TYPE
     *
     * @mbggenerated
     */
    public void setSpeType(String speType) {
        this.speType = speType == null ? null : speType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.CREATER_ID
     *
     * @return the value of product_sku.CREATER_ID
     *
     * @mbggenerated
     */
    public Integer getCreaterId() {
        return createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.CREATER_ID
     *
     * @param createrId the value for product_sku.CREATER_ID
     *
     * @mbggenerated
     */
    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.CREATE_TIME
     *
     * @return the value of product_sku.CREATE_TIME
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.CREATE_TIME
     *
     * @param createTime the value for product_sku.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.MODIFIER_ID
     *
     * @return the value of product_sku.MODIFIER_ID
     *
     * @mbggenerated
     */
    public Integer getModifierId() {
        return modifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.MODIFIER_ID
     *
     * @param modifierId the value for product_sku.MODIFIER_ID
     *
     * @mbggenerated
     */
    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.MODIFY_TIME
     *
     * @return the value of product_sku.MODIFY_TIME
     *
     * @mbggenerated
     */
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.MODIFY_TIME
     *
     * @param modifyTime the value for product_sku.MODIFY_TIME
     *
     * @mbggenerated
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.IS_RANGEPHONE
     *
     * @return the value of product_sku.IS_RANGEPHONE
     *
     * @mbggenerated
     */
    public String getIsRangephone() {
        return isRangephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.IS_RANGEPHONE
     *
     * @param isRangephone the value for product_sku.IS_RANGEPHONE
     *
     * @mbggenerated
     */
    public void setIsRangephone(String isRangephone) {
        this.isRangephone = isRangephone == null ? null : isRangephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.RANGE_PRICE
     *
     * @return the value of product_sku.RANGE_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getRangePrice() {
        return rangePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.RANGE_PRICE
     *
     * @param rangePrice the value for product_sku.RANGE_PRICE
     *
     * @mbggenerated
     */
    public void setRangePrice(BigDecimal rangePrice) {
        this.rangePrice = rangePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.COMMENTS
     *
     * @return the value of product_sku.COMMENTS
     *
     * @mbggenerated
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.COMMENTS
     *
     * @param comments the value for product_sku.COMMENTS
     *
     * @mbggenerated
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.LISTPIC
     *
     * @return the value of product_sku.LISTPIC
     *
     * @mbggenerated
     */
    public Long getListpic() {
        return listpic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.LISTPIC
     *
     * @param listpic the value for product_sku.LISTPIC
     *
     * @mbggenerated
     */
    public void setListpic(Long listpic) {
        this.listpic = listpic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.GOOD_TYPE
     *
     * @return the value of product_sku.GOOD_TYPE
     *
     * @mbggenerated
     */
    public String getGoodType() {
        return goodType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.GOOD_TYPE
     *
     * @param goodType the value for product_sku.GOOD_TYPE
     *
     * @mbggenerated
     */
    public void setGoodType(String goodType) {
        this.goodType = goodType == null ? null : goodType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_sku.BSS_GOOD_ID
     *
     * @return the value of product_sku.BSS_GOOD_ID
     *
     * @mbggenerated
     */
    public String getBssGoodId() {
        return bssGoodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_sku.BSS_GOOD_ID
     *
     * @param bssGoodId the value for product_sku.BSS_GOOD_ID
     *
     * @mbggenerated
     */
    public void setBssGoodId(String bssGoodId) {
        this.bssGoodId = bssGoodId == null ? null : bssGoodId.trim();
    }
}