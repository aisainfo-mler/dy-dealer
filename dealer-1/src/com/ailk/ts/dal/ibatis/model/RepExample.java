package com.ailk.ts.dal.ibatis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected Integer limitClauseStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected Integer limitClauseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected String suffix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public RepExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected RepExample(RepExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void setLimitClauseStart(Integer limitClauseStart) {
        this.limitClauseStart = limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public Integer getLimitClauseStart() {
        return limitClauseStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void setLimitClauseCount(Integer limitClauseCount) {
        this.limitClauseCount = limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public Integer getLimitClauseCount() {
        return limitClauseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
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
     * This method corresponds to the database table rep
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
     * This method corresponds to the database table rep
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep
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
     * This class corresponds to the database table rep
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rep
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
    
    
    private Integer brandId;
    private Integer productId;
    private Integer productTypeId;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	
	
	/**
	 * 判断，如果3个参数中存在任意一个，则需要关联其他几个表查询。
	 * @return
	 */
	public String getAppend(){
		boolean b = this.brandId!=null || this.productId!=null || this.productTypeId!=null;
		if(b==true){
			return "exist";
		}
		return "";
		
	}
    
}