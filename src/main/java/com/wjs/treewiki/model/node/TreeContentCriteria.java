package com.wjs.treewiki.model.node;

import java.util.ArrayList;
import java.util.List;

public class TreeContentCriteria {
    /**
     * tree_content表的操作属性:orderByClause
     * 
     */
    protected String orderByClause;

    /**
     * tree_content表的操作属性:start
     * 
     */
    protected int start;

    /**
     * tree_content表的操作属性:limit
     * 
     */
    protected int limit;

    /**
     * tree_content表的操作属性:distinct
     * 
     */
    protected boolean distinct;

    /**
     * tree_content表的操作属性:oredCriteria
     * 
     */
    protected List<Criteria> oredCriteria;

    /**
     * tree_content数据表的操作方法: TreeContentCriteria  
     * 
     */
    public TreeContentCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * tree_content数据表的操作方法: setOrderByClause  
     * 
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * tree_content数据表的操作方法: getOrderByClause  
     * 
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * tree_content数据表的操作方法: setStart  
     * 
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * tree_content数据表的操作方法: getStart  
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * tree_content数据表的操作方法: setLimit  
     * 
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * tree_content数据表的操作方法: getLimit  
     * 
     */
    public int getLimit() {
        return limit;
    }

    /**
     * tree_content数据表的操作方法: setDistinct  
     * 
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * tree_content数据表的操作方法: isDistinct  
     * 
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * tree_content数据表的操作方法: getOredCriteria  
     * 
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * tree_content数据表的操作方法: or  
     * 
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * tree_content数据表的操作方法: or  
     * 
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * tree_content数据表的操作方法: createCriteria  
     * 
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * tree_content数据表的操作方法: createCriteriaInternal  
     * 
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * tree_content数据表的操作方法: clear  
     * 
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * tree_content表的操作类
     * 
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andLockbyIsNull() {
            addCriterion("lockby is null");
            return (Criteria) this;
        }

        public Criteria andLockbyIsNotNull() {
            addCriterion("lockby is not null");
            return (Criteria) this;
        }

        public Criteria andLockbyEqualTo(String value) {
            addCriterion("lockby =", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyNotEqualTo(String value) {
            addCriterion("lockby <>", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyGreaterThan(String value) {
            addCriterion("lockby >", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyGreaterThanOrEqualTo(String value) {
            addCriterion("lockby >=", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyLessThan(String value) {
            addCriterion("lockby <", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyLessThanOrEqualTo(String value) {
            addCriterion("lockby <=", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyLike(String value) {
            addCriterion("lockby like", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyNotLike(String value) {
            addCriterion("lockby not like", value, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyIn(List<String> values) {
            addCriterion("lockby in", values, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyNotIn(List<String> values) {
            addCriterion("lockby not in", values, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyBetween(String value1, String value2) {
            addCriterion("lockby between", value1, value2, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyNotBetween(String value1, String value2) {
            addCriterion("lockby not between", value1, value2, "lockby");
            return (Criteria) this;
        }

        public Criteria andLockbyIdIsNull() {
            addCriterion("lockby_id is null");
            return (Criteria) this;
        }

        public Criteria andLockbyIdIsNotNull() {
            addCriterion("lockby_id is not null");
            return (Criteria) this;
        }

        public Criteria andLockbyIdEqualTo(Long value) {
            addCriterion("lockby_id =", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdNotEqualTo(Long value) {
            addCriterion("lockby_id <>", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdGreaterThan(Long value) {
            addCriterion("lockby_id >", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("lockby_id >=", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdLessThan(Long value) {
            addCriterion("lockby_id <", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdLessThanOrEqualTo(Long value) {
            addCriterion("lockby_id <=", value, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdIn(List<Long> values) {
            addCriterion("lockby_id in", values, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdNotIn(List<Long> values) {
            addCriterion("lockby_id not in", values, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdBetween(Long value1, Long value2) {
            addCriterion("lockby_id between", value1, value2, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyIdNotBetween(Long value1, Long value2) {
            addCriterion("lockby_id not between", value1, value2, "lockbyId");
            return (Criteria) this;
        }

        public Criteria andLockbyNameIsNull() {
            addCriterion("lockby_name is null");
            return (Criteria) this;
        }

        public Criteria andLockbyNameIsNotNull() {
            addCriterion("lockby_name is not null");
            return (Criteria) this;
        }

        public Criteria andLockbyNameEqualTo(String value) {
            addCriterion("lockby_name =", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameNotEqualTo(String value) {
            addCriterion("lockby_name <>", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameGreaterThan(String value) {
            addCriterion("lockby_name >", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameGreaterThanOrEqualTo(String value) {
            addCriterion("lockby_name >=", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameLessThan(String value) {
            addCriterion("lockby_name <", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameLessThanOrEqualTo(String value) {
            addCriterion("lockby_name <=", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameLike(String value) {
            addCriterion("lockby_name like", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameNotLike(String value) {
            addCriterion("lockby_name not like", value, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameIn(List<String> values) {
            addCriterion("lockby_name in", values, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameNotIn(List<String> values) {
            addCriterion("lockby_name not in", values, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameBetween(String value1, String value2) {
            addCriterion("lockby_name between", value1, value2, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andLockbyNameNotBetween(String value1, String value2) {
            addCriterion("lockby_name not between", value1, value2, "lockbyName");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIsNull() {
            addCriterion("create_datetime is null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIsNotNull() {
            addCriterion("create_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeEqualTo(Long value) {
            addCriterion("create_datetime =", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotEqualTo(Long value) {
            addCriterion("create_datetime <>", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThan(Long value) {
            addCriterion("create_datetime >", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_datetime >=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThan(Long value) {
            addCriterion("create_datetime <", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeLessThanOrEqualTo(Long value) {
            addCriterion("create_datetime <=", value, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeIn(List<Long> values) {
            addCriterion("create_datetime in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotIn(List<Long> values) {
            addCriterion("create_datetime not in", values, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeBetween(Long value1, Long value2) {
            addCriterion("create_datetime between", value1, value2, "createDatetime");
            return (Criteria) this;
        }

        public Criteria andCreateDatetimeNotBetween(Long value1, Long value2) {
            addCriterion("create_datetime not between", value1, value2, "createDatetime");
            return (Criteria) this;
        }
    }

    /**
     * tree_content表的操作类
     * 
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tree_content表的操作类
     * 
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}