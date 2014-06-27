package com.ailk.ts.dal.ibatis.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkuEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected Integer limitClauseStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected Integer limitClauseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected String suffix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public SkuEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected SkuEntityExample(SkuEntityExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void setLimitClauseStart(Integer limitClauseStart) {
        this.limitClauseStart = limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public Integer getLimitClauseStart() {
        return limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void setLimitClauseCount(Integer limitClauseCount) {
        this.limitClauseCount = limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public Integer getLimitClauseCount() {
        return limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sku_entity
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected GeneratedCriteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andEntityIdIsNull() {
            addCriterion("ENTITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNotNull() {
            addCriterion("ENTITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEntityIdEqualTo(Integer value) {
            addCriterion("ENTITY_ID =", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotEqualTo(Integer value) {
            addCriterion("ENTITY_ID <>", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThan(Integer value) {
            addCriterion("ENTITY_ID >", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ENTITY_ID >=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThan(Integer value) {
            addCriterion("ENTITY_ID <", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThanOrEqualTo(Integer value) {
            addCriterion("ENTITY_ID <=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdIn(List<Integer> values) {
            addCriterion("ENTITY_ID in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotIn(List<Integer> values) {
            addCriterion("ENTITY_ID not in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdBetween(Integer value1, Integer value2) {
            addCriterion("ENTITY_ID between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ENTITY_ID not between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNull() {
            addCriterion("SKUID is null");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNotNull() {
            addCriterion("SKUID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuidEqualTo(Integer value) {
            addCriterion("SKUID =", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotEqualTo(Integer value) {
            addCriterion("SKUID <>", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThan(Integer value) {
            addCriterion("SKUID >", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKUID >=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThan(Integer value) {
            addCriterion("SKUID <", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThanOrEqualTo(Integer value) {
            addCriterion("SKUID <=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidIn(List<Integer> values) {
            addCriterion("SKUID in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotIn(List<Integer> values) {
            addCriterion("SKUID not in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidBetween(Integer value1, Integer value2) {
            addCriterion("SKUID between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotBetween(Integer value1, Integer value2) {
            addCriterion("SKUID not between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeIsNull() {
            addCriterion("REPOSITORY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeIsNotNull() {
            addCriterion("REPOSITORY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeEqualTo(String value) {
            addCriterion("REPOSITORY_CODE =", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeNotEqualTo(String value) {
            addCriterion("REPOSITORY_CODE <>", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeGreaterThan(String value) {
            addCriterion("REPOSITORY_CODE >", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REPOSITORY_CODE >=", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeLessThan(String value) {
            addCriterion("REPOSITORY_CODE <", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeLessThanOrEqualTo(String value) {
            addCriterion("REPOSITORY_CODE <=", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeLike(String value) {
            addCriterion("REPOSITORY_CODE like", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeNotLike(String value) {
            addCriterion("REPOSITORY_CODE not like", value, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeIn(List<String> values) {
            addCriterion("REPOSITORY_CODE in", values, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeNotIn(List<String> values) {
            addCriterion("REPOSITORY_CODE not in", values, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeBetween(String value1, String value2) {
            addCriterion("REPOSITORY_CODE between", value1, value2, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andRepositoryCodeNotBetween(String value1, String value2) {
            addCriterion("REPOSITORY_CODE not between", value1, value2, "repositoryCode");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("IMEI is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("IMEI is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("IMEI =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("IMEI <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("IMEI >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("IMEI >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("IMEI <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("IMEI <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("IMEI like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("IMEI not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("IMEI in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("IMEI not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("IMEI between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("IMEI not between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andSerialIsNull() {
            addCriterion("SERIAL is null");
            return (Criteria) this;
        }

        public Criteria andSerialIsNotNull() {
            addCriterion("SERIAL is not null");
            return (Criteria) this;
        }

        public Criteria andSerialEqualTo(String value) {
            addCriterion("SERIAL =", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotEqualTo(String value) {
            addCriterion("SERIAL <>", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThan(String value) {
            addCriterion("SERIAL >", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThanOrEqualTo(String value) {
            addCriterion("SERIAL >=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThan(String value) {
            addCriterion("SERIAL <", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThanOrEqualTo(String value) {
            addCriterion("SERIAL <=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLike(String value) {
            addCriterion("SERIAL like", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotLike(String value) {
            addCriterion("SERIAL not like", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialIn(List<String> values) {
            addCriterion("SERIAL in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotIn(List<String> values) {
            addCriterion("SERIAL not in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialBetween(String value1, String value2) {
            addCriterion("SERIAL between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotBetween(String value1, String value2) {
            addCriterion("SERIAL not between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("COST_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("COST_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(BigDecimal value) {
            addCriterion("COST_PRICE >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(BigDecimal value) {
            addCriterion("COST_PRICE <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<BigDecimal> values) {
            addCriterion("COST_PRICE in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<BigDecimal> values) {
            addCriterion("COST_PRICE not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_PRICE between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_PRICE not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNull() {
            addCriterion("SELL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNotNull() {
            addCriterion("SELL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSellPriceEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE =", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE <>", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThan(BigDecimal value) {
            addCriterion("SELL_PRICE >", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE >=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThan(BigDecimal value) {
            addCriterion("SELL_PRICE <", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SELL_PRICE <=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIn(List<BigDecimal> values) {
            addCriterion("SELL_PRICE in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("SELL_PRICE not in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SELL_PRICE between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SELL_PRICE not between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Timestamp value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Timestamp value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Timestamp value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Timestamp value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Timestamp> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Timestamp> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartIsNull() {
            addCriterion("SERVICE_START is null");
            return (Criteria) this;
        }

        public Criteria andServiceStartIsNotNull() {
            addCriterion("SERVICE_START is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStartEqualTo(Timestamp value) {
            addCriterion("SERVICE_START =", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotEqualTo(Timestamp value) {
            addCriterion("SERVICE_START <>", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartGreaterThan(Timestamp value) {
            addCriterion("SERVICE_START >", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SERVICE_START >=", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartLessThan(Timestamp value) {
            addCriterion("SERVICE_START <", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartLessThanOrEqualTo(Timestamp value) {
            addCriterion("SERVICE_START <=", value, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartIn(List<Timestamp> values) {
            addCriterion("SERVICE_START in", values, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotIn(List<Timestamp> values) {
            addCriterion("SERVICE_START not in", values, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SERVICE_START between", value1, value2, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceStartNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SERVICE_START not between", value1, value2, "serviceStart");
            return (Criteria) this;
        }

        public Criteria andServiceEndIsNull() {
            addCriterion("SERVICE_END is null");
            return (Criteria) this;
        }

        public Criteria andServiceEndIsNotNull() {
            addCriterion("SERVICE_END is not null");
            return (Criteria) this;
        }

        public Criteria andServiceEndEqualTo(Timestamp value) {
            addCriterion("SERVICE_END =", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotEqualTo(Timestamp value) {
            addCriterion("SERVICE_END <>", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndGreaterThan(Timestamp value) {
            addCriterion("SERVICE_END >", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SERVICE_END >=", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndLessThan(Timestamp value) {
            addCriterion("SERVICE_END <", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndLessThanOrEqualTo(Timestamp value) {
            addCriterion("SERVICE_END <=", value, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndIn(List<Timestamp> values) {
            addCriterion("SERVICE_END in", values, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotIn(List<Timestamp> values) {
            addCriterion("SERVICE_END not in", values, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SERVICE_END between", value1, value2, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andServiceEndNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SERVICE_END not between", value1, value2, "serviceEnd");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeIsNull() {
            addCriterion("TARGET_REPCODE is null");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeIsNotNull() {
            addCriterion("TARGET_REPCODE is not null");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeEqualTo(String value) {
            addCriterion("TARGET_REPCODE =", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeNotEqualTo(String value) {
            addCriterion("TARGET_REPCODE <>", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeGreaterThan(String value) {
            addCriterion("TARGET_REPCODE >", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_REPCODE >=", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeLessThan(String value) {
            addCriterion("TARGET_REPCODE <", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeLessThanOrEqualTo(String value) {
            addCriterion("TARGET_REPCODE <=", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeLike(String value) {
            addCriterion("TARGET_REPCODE like", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeNotLike(String value) {
            addCriterion("TARGET_REPCODE not like", value, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeIn(List<String> values) {
            addCriterion("TARGET_REPCODE in", values, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeNotIn(List<String> values) {
            addCriterion("TARGET_REPCODE not in", values, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeBetween(String value1, String value2) {
            addCriterion("TARGET_REPCODE between", value1, value2, "targetRepcode");
            return (Criteria) this;
        }

        public Criteria andTargetRepcodeNotBetween(String value1, String value2) {
            addCriterion("TARGET_REPCODE not between", value1, value2, "targetRepcode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sku_entity
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}