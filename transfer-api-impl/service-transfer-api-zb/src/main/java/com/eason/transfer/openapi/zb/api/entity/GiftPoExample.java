package com.eason.transfer.openapi.zb.api.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GiftPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GiftPoExample() {
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

        public Criteria andGiftNameIsNull() {
            addCriterion("gift_name is null");
            return (Criteria) this;
        }

        public Criteria andGiftNameIsNotNull() {
            addCriterion("gift_name is not null");
            return (Criteria) this;
        }

        public Criteria andGiftNameEqualTo(String value) {
            addCriterion("gift_name =", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotEqualTo(String value) {
            addCriterion("gift_name <>", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameGreaterThan(String value) {
            addCriterion("gift_name >", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameGreaterThanOrEqualTo(String value) {
            addCriterion("gift_name >=", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLessThan(String value) {
            addCriterion("gift_name <", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLessThanOrEqualTo(String value) {
            addCriterion("gift_name <=", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLike(String value) {
            addCriterion("gift_name like", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotLike(String value) {
            addCriterion("gift_name not like", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameIn(List<String> values) {
            addCriterion("gift_name in", values, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotIn(List<String> values) {
            addCriterion("gift_name not in", values, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameBetween(String value1, String value2) {
            addCriterion("gift_name between", value1, value2, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotBetween(String value1, String value2) {
            addCriterion("gift_name not between", value1, value2, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftLmgIsNull() {
            addCriterion("gift_lmg is null");
            return (Criteria) this;
        }

        public Criteria andGiftLmgIsNotNull() {
            addCriterion("gift_lmg is not null");
            return (Criteria) this;
        }

        public Criteria andGiftLmgEqualTo(String value) {
            addCriterion("gift_lmg =", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgNotEqualTo(String value) {
            addCriterion("gift_lmg <>", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgGreaterThan(String value) {
            addCriterion("gift_lmg >", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgGreaterThanOrEqualTo(String value) {
            addCriterion("gift_lmg >=", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgLessThan(String value) {
            addCriterion("gift_lmg <", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgLessThanOrEqualTo(String value) {
            addCriterion("gift_lmg <=", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgLike(String value) {
            addCriterion("gift_lmg like", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgNotLike(String value) {
            addCriterion("gift_lmg not like", value, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgIn(List<String> values) {
            addCriterion("gift_lmg in", values, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgNotIn(List<String> values) {
            addCriterion("gift_lmg not in", values, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgBetween(String value1, String value2) {
            addCriterion("gift_lmg between", value1, value2, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftLmgNotBetween(String value1, String value2) {
            addCriterion("gift_lmg not between", value1, value2, "giftLmg");
            return (Criteria) this;
        }

        public Criteria andGiftPriceIsNull() {
            addCriterion("gift_price is null");
            return (Criteria) this;
        }

        public Criteria andGiftPriceIsNotNull() {
            addCriterion("gift_price is not null");
            return (Criteria) this;
        }

        public Criteria andGiftPriceEqualTo(BigDecimal value) {
            addCriterion("gift_price =", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceNotEqualTo(BigDecimal value) {
            addCriterion("gift_price <>", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceGreaterThan(BigDecimal value) {
            addCriterion("gift_price >", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("gift_price >=", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceLessThan(BigDecimal value) {
            addCriterion("gift_price <", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("gift_price <=", value, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceIn(List<BigDecimal> values) {
            addCriterion("gift_price in", values, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceNotIn(List<BigDecimal> values) {
            addCriterion("gift_price not in", values, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gift_price between", value1, value2, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andGiftPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gift_price not between", value1, value2, "giftPrice");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleIsNull() {
            addCriterion("specialStyle is null");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleIsNotNull() {
            addCriterion("specialStyle is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleEqualTo(String value) {
            addCriterion("specialStyle =", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleNotEqualTo(String value) {
            addCriterion("specialStyle <>", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleGreaterThan(String value) {
            addCriterion("specialStyle >", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleGreaterThanOrEqualTo(String value) {
            addCriterion("specialStyle >=", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleLessThan(String value) {
            addCriterion("specialStyle <", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleLessThanOrEqualTo(String value) {
            addCriterion("specialStyle <=", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleLike(String value) {
            addCriterion("specialStyle like", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleNotLike(String value) {
            addCriterion("specialStyle not like", value, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleIn(List<String> values) {
            addCriterion("specialStyle in", values, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleNotIn(List<String> values) {
            addCriterion("specialStyle not in", values, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleBetween(String value1, String value2) {
            addCriterion("specialStyle between", value1, value2, "specialstyle");
            return (Criteria) this;
        }

        public Criteria andSpecialstyleNotBetween(String value1, String value2) {
            addCriterion("specialStyle not between", value1, value2, "specialstyle");
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