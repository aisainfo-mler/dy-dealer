package com.ailk.ts.dal.ibatis.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepOptRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected Integer limitClauseStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected Integer limitClauseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected String suffix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public RepOptRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected RepOptRecordExample(RepOptRecordExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void setLimitClauseStart(Integer limitClauseStart) {
        this.limitClauseStart = limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public Integer getLimitClauseStart() {
        return limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void setLimitClauseCount(Integer limitClauseCount) {
        this.limitClauseCount = limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public Integer getLimitClauseCount() {
        return limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
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
     * This method corresponds to the database table rep_opt_record
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
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
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
     * This class corresponds to the database table rep_opt_record
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

        public Criteria andSerialNoIsNull() {
            addCriterion("SERIAL_NO is null");
            return (Criteria) this;
        }

        public Criteria andSerialNoIsNotNull() {
            addCriterion("SERIAL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNoEqualTo(Long value) {
            addCriterion("SERIAL_NO =", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotEqualTo(Long value) {
            addCriterion("SERIAL_NO <>", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThan(Long value) {
            addCriterion("SERIAL_NO >", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThanOrEqualTo(Long value) {
            addCriterion("SERIAL_NO >=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThan(Long value) {
            addCriterion("SERIAL_NO <", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThanOrEqualTo(Long value) {
            addCriterion("SERIAL_NO <=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoIn(List<Long> values) {
            addCriterion("SERIAL_NO in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotIn(List<Long> values) {
            addCriterion("SERIAL_NO not in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoBetween(Long value1, Long value2) {
            addCriterion("SERIAL_NO between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotBetween(Long value1, Long value2) {
            addCriterion("SERIAL_NO not between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeIsNull() {
            addCriterion("INPUT_REP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeIsNotNull() {
            addCriterion("INPUT_REP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeEqualTo(Long value) {
            addCriterion("INPUT_REP_CODE =", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeNotEqualTo(Long value) {
            addCriterion("INPUT_REP_CODE <>", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeGreaterThan(Long value) {
            addCriterion("INPUT_REP_CODE >", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("INPUT_REP_CODE >=", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeLessThan(Long value) {
            addCriterion("INPUT_REP_CODE <", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeLessThanOrEqualTo(Long value) {
            addCriterion("INPUT_REP_CODE <=", value, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeIn(List<Long> values) {
            addCriterion("INPUT_REP_CODE in", values, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeNotIn(List<Long> values) {
            addCriterion("INPUT_REP_CODE not in", values, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeBetween(Long value1, Long value2) {
            addCriterion("INPUT_REP_CODE between", value1, value2, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andInputRepCodeNotBetween(Long value1, Long value2) {
            addCriterion("INPUT_REP_CODE not between", value1, value2, "inputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeIsNull() {
            addCriterion("OUTPUT_REP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeIsNotNull() {
            addCriterion("OUTPUT_REP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeEqualTo(Long value) {
            addCriterion("OUTPUT_REP_CODE =", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeNotEqualTo(Long value) {
            addCriterion("OUTPUT_REP_CODE <>", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeGreaterThan(Long value) {
            addCriterion("OUTPUT_REP_CODE >", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("OUTPUT_REP_CODE >=", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeLessThan(Long value) {
            addCriterion("OUTPUT_REP_CODE <", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeLessThanOrEqualTo(Long value) {
            addCriterion("OUTPUT_REP_CODE <=", value, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeIn(List<Long> values) {
            addCriterion("OUTPUT_REP_CODE in", values, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeNotIn(List<Long> values) {
            addCriterion("OUTPUT_REP_CODE not in", values, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeBetween(Long value1, Long value2) {
            addCriterion("OUTPUT_REP_CODE between", value1, value2, "outputRepCode");
            return (Criteria) this;
        }

        public Criteria andOutputRepCodeNotBetween(Long value1, Long value2) {
            addCriterion("OUTPUT_REP_CODE not between", value1, value2, "outputRepCode");
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

        public Criteria andSkuidEqualTo(Long value) {
            addCriterion("SKUID =", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotEqualTo(Long value) {
            addCriterion("SKUID <>", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThan(Long value) {
            addCriterion("SKUID >", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThanOrEqualTo(Long value) {
            addCriterion("SKUID >=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThan(Long value) {
            addCriterion("SKUID <", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThanOrEqualTo(Long value) {
            addCriterion("SKUID <=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidIn(List<Long> values) {
            addCriterion("SKUID in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotIn(List<Long> values) {
            addCriterion("SKUID not in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidBetween(Long value1, Long value2) {
            addCriterion("SKUID between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotBetween(Long value1, Long value2) {
            addCriterion("SKUID not between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("OPT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("OPT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(String value) {
            addCriterion("OPT_TYPE =", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(String value) {
            addCriterion("OPT_TYPE <>", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(String value) {
            addCriterion("OPT_TYPE >", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OPT_TYPE >=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(String value) {
            addCriterion("OPT_TYPE <", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(String value) {
            addCriterion("OPT_TYPE <=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLike(String value) {
            addCriterion("OPT_TYPE like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotLike(String value) {
            addCriterion("OPT_TYPE not like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<String> values) {
            addCriterion("OPT_TYPE in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<String> values) {
            addCriterion("OPT_TYPE not in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(String value1, String value2) {
            addCriterion("OPT_TYPE between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(String value1, String value2) {
            addCriterion("OPT_TYPE not between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("COUNT =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("COUNT <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("COUNT >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUNT >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("COUNT <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("COUNT <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("COUNT in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("COUNT not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("COUNT between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("COUNT not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOptIdIsNull() {
            addCriterion("OPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOptIdIsNotNull() {
            addCriterion("OPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOptIdEqualTo(Long value) {
            addCriterion("OPT_ID =", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotEqualTo(Long value) {
            addCriterion("OPT_ID <>", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThan(Long value) {
            addCriterion("OPT_ID >", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OPT_ID >=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThan(Long value) {
            addCriterion("OPT_ID <", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThanOrEqualTo(Long value) {
            addCriterion("OPT_ID <=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdIn(List<Long> values) {
            addCriterion("OPT_ID in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotIn(List<Long> values) {
            addCriterion("OPT_ID not in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdBetween(Long value1, Long value2) {
            addCriterion("OPT_ID between", value1, value2, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotBetween(Long value1, Long value2) {
            addCriterion("OPT_ID not between", value1, value2, "optId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rep_opt_record
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}