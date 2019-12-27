package com.eason.transfer.openapi.chess.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TReportDayUserPoExample {

    private Integer startRow;

    private Integer pageSize;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public TReportDayUserPoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andSiteIdIsNull() {
            addCriterion("site_id is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("site_id is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(String value) {
            addCriterion("site_id =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(String value) {
            addCriterion("site_id <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(String value) {
            addCriterion("site_id >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(String value) {
            addCriterion("site_id >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(String value) {
            addCriterion("site_id <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(String value) {
            addCriterion("site_id <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLike(String value) {
            addCriterion("site_id like", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotLike(String value) {
            addCriterion("site_id not like", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<String> values) {
            addCriterion("site_id in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<String> values) {
            addCriterion("site_id not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(String value1, String value2) {
            addCriterion("site_id between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(String value1, String value2) {
            addCriterion("site_id not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andGameKindIsNull() {
            addCriterion("game_kind is null");
            return (Criteria) this;
        }

        public Criteria andGameKindIsNotNull() {
            addCriterion("game_kind is not null");
            return (Criteria) this;
        }

        public Criteria andGameKindEqualTo(String value) {
            addCriterion("game_kind =", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindNotEqualTo(String value) {
            addCriterion("game_kind <>", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindGreaterThan(String value) {
            addCriterion("game_kind >", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindGreaterThanOrEqualTo(String value) {
            addCriterion("game_kind >=", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindLessThan(String value) {
            addCriterion("game_kind <", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindLessThanOrEqualTo(String value) {
            addCriterion("game_kind <=", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindLike(String value) {
            addCriterion("game_kind like", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindNotLike(String value) {
            addCriterion("game_kind not like", value, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindIn(List<String> values) {
            addCriterion("game_kind in", values, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindNotIn(List<String> values) {
            addCriterion("game_kind not in", values, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindBetween(String value1, String value2) {
            addCriterion("game_kind between", value1, value2, "gameKind");
            return (Criteria) this;
        }

        public Criteria andGameKindNotBetween(String value1, String value2) {
            addCriterion("game_kind not between", value1, value2, "gameKind");
            return (Criteria) this;
        }

        public Criteria andBetCountsIsNull() {
            addCriterion("bet_counts is null");
            return (Criteria) this;
        }

        public Criteria andBetCountsIsNotNull() {
            addCriterion("bet_counts is not null");
            return (Criteria) this;
        }

        public Criteria andBetCountsEqualTo(Integer value) {
            addCriterion("bet_counts =", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsNotEqualTo(Integer value) {
            addCriterion("bet_counts <>", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsGreaterThan(Integer value) {
            addCriterion("bet_counts >", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("bet_counts >=", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsLessThan(Integer value) {
            addCriterion("bet_counts <", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsLessThanOrEqualTo(Integer value) {
            addCriterion("bet_counts <=", value, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsIn(List<Integer> values) {
            addCriterion("bet_counts in", values, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsNotIn(List<Integer> values) {
            addCriterion("bet_counts not in", values, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsBetween(Integer value1, Integer value2) {
            addCriterion("bet_counts between", value1, value2, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetCountsNotBetween(Integer value1, Integer value2) {
            addCriterion("bet_counts not between", value1, value2, "betCounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsIsNull() {
            addCriterion("bet_amounts is null");
            return (Criteria) this;
        }

        public Criteria andBetAmountsIsNotNull() {
            addCriterion("bet_amounts is not null");
            return (Criteria) this;
        }

        public Criteria andBetAmountsEqualTo(BigDecimal value) {
            addCriterion("bet_amounts =", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsNotEqualTo(BigDecimal value) {
            addCriterion("bet_amounts <>", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsGreaterThan(BigDecimal value) {
            addCriterion("bet_amounts >", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_amounts >=", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsLessThan(BigDecimal value) {
            addCriterion("bet_amounts <", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_amounts <=", value, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsIn(List<BigDecimal> values) {
            addCriterion("bet_amounts in", values, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsNotIn(List<BigDecimal> values) {
            addCriterion("bet_amounts not in", values, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_amounts between", value1, value2, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andBetAmountsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_amounts not between", value1, value2, "betAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsIsNull() {
            addCriterion("valid_amounts is null");
            return (Criteria) this;
        }

        public Criteria andValidAmountsIsNotNull() {
            addCriterion("valid_amounts is not null");
            return (Criteria) this;
        }

        public Criteria andValidAmountsEqualTo(BigDecimal value) {
            addCriterion("valid_amounts =", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsNotEqualTo(BigDecimal value) {
            addCriterion("valid_amounts <>", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsGreaterThan(BigDecimal value) {
            addCriterion("valid_amounts >", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("valid_amounts >=", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsLessThan(BigDecimal value) {
            addCriterion("valid_amounts <", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("valid_amounts <=", value, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsIn(List<BigDecimal> values) {
            addCriterion("valid_amounts in", values, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsNotIn(List<BigDecimal> values) {
            addCriterion("valid_amounts not in", values, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("valid_amounts between", value1, value2, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andValidAmountsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("valid_amounts not between", value1, value2, "validAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsIsNull() {
            addCriterion("pay_amounts is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountsIsNotNull() {
            addCriterion("pay_amounts is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountsEqualTo(BigDecimal value) {
            addCriterion("pay_amounts =", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsNotEqualTo(BigDecimal value) {
            addCriterion("pay_amounts <>", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsGreaterThan(BigDecimal value) {
            addCriterion("pay_amounts >", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amounts >=", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsLessThan(BigDecimal value) {
            addCriterion("pay_amounts <", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amounts <=", value, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsIn(List<BigDecimal> values) {
            addCriterion("pay_amounts in", values, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsNotIn(List<BigDecimal> values) {
            addCriterion("pay_amounts not in", values, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amounts between", value1, value2, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andPayAmountsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amounts not between", value1, value2, "payAmounts");
            return (Criteria) this;
        }

        public Criteria andBetTimeIsNull() {
            addCriterion("bet_time is null");
            return (Criteria) this;
        }

        public Criteria andBetTimeIsNotNull() {
            addCriterion("bet_time is not null");
            return (Criteria) this;
        }

        public Criteria andBetTimeEqualTo(Date value) {
            addCriterionForJDBCDate("bet_time =", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("bet_time <>", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("bet_time >", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bet_time >=", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeLessThan(Date value) {
            addCriterionForJDBCDate("bet_time <", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bet_time <=", value, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeIn(List<Date> values) {
            addCriterionForJDBCDate("bet_time in", values, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("bet_time not in", values, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bet_time between", value1, value2, "betTime");
            return (Criteria) this;
        }

        public Criteria andBetTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bet_time not between", value1, value2, "betTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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