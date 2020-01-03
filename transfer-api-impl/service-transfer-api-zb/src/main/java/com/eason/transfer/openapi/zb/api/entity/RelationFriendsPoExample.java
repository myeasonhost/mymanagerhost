package com.eason.transfer.openapi.zb.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelationFriendsPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RelationFriendsPoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_Id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_Id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_Id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_Id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_Id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_Id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_Id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_Id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_Id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_Id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_Id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_Id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_Id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_Id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRelationUseridIsNull() {
            addCriterion("relation_userId is null");
            return (Criteria) this;
        }

        public Criteria andRelationUseridIsNotNull() {
            addCriterion("relation_userId is not null");
            return (Criteria) this;
        }

        public Criteria andRelationUseridEqualTo(String value) {
            addCriterion("relation_userId =", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridNotEqualTo(String value) {
            addCriterion("relation_userId <>", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridGreaterThan(String value) {
            addCriterion("relation_userId >", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridGreaterThanOrEqualTo(String value) {
            addCriterion("relation_userId >=", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridLessThan(String value) {
            addCriterion("relation_userId <", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridLessThanOrEqualTo(String value) {
            addCriterion("relation_userId <=", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridLike(String value) {
            addCriterion("relation_userId like", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridNotLike(String value) {
            addCriterion("relation_userId not like", value, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridIn(List<String> values) {
            addCriterion("relation_userId in", values, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridNotIn(List<String> values) {
            addCriterion("relation_userId not in", values, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridBetween(String value1, String value2) {
            addCriterion("relation_userId between", value1, value2, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationUseridNotBetween(String value1, String value2) {
            addCriterion("relation_userId not between", value1, value2, "relationUserid");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIsNull() {
            addCriterion("relation_time is null");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIsNotNull() {
            addCriterion("relation_time is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTimeEqualTo(Date value) {
            addCriterion("relation_time =", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotEqualTo(Date value) {
            addCriterion("relation_time <>", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeGreaterThan(Date value) {
            addCriterion("relation_time >", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("relation_time >=", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeLessThan(Date value) {
            addCriterion("relation_time <", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeLessThanOrEqualTo(Date value) {
            addCriterion("relation_time <=", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIn(List<Date> values) {
            addCriterion("relation_time in", values, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotIn(List<Date> values) {
            addCriterion("relation_time not in", values, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeBetween(Date value1, Date value2) {
            addCriterion("relation_time between", value1, value2, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotBetween(Date value1, Date value2) {
            addCriterion("relation_time not between", value1, value2, "relationTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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