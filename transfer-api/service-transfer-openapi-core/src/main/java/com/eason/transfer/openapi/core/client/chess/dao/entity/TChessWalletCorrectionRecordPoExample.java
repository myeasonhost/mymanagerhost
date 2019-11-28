package com.eason.transfer.openapi.core.client.chess.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TChessWalletCorrectionRecordPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TChessWalletCorrectionRecordPoExample() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("record_id like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("record_id not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andGameKindAIsNull() {
            addCriterion("game_kind_A is null");
            return (Criteria) this;
        }

        public Criteria andGameKindAIsNotNull() {
            addCriterion("game_kind_A is not null");
            return (Criteria) this;
        }

        public Criteria andGameKindAEqualTo(String value) {
            addCriterion("game_kind_A =", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindANotEqualTo(String value) {
            addCriterion("game_kind_A <>", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindAGreaterThan(String value) {
            addCriterion("game_kind_A >", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindAGreaterThanOrEqualTo(String value) {
            addCriterion("game_kind_A >=", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindALessThan(String value) {
            addCriterion("game_kind_A <", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindALessThanOrEqualTo(String value) {
            addCriterion("game_kind_A <=", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindALike(String value) {
            addCriterion("game_kind_A like", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindANotLike(String value) {
            addCriterion("game_kind_A not like", value, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindAIn(List<String> values) {
            addCriterion("game_kind_A in", values, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindANotIn(List<String> values) {
            addCriterion("game_kind_A not in", values, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindABetween(String value1, String value2) {
            addCriterion("game_kind_A between", value1, value2, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andGameKindANotBetween(String value1, String value2) {
            addCriterion("game_kind_A not between", value1, value2, "gameKindA");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("opt_type is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("opt_type is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(String value) {
            addCriterion("opt_type =", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(String value) {
            addCriterion("opt_type <>", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(String value) {
            addCriterion("opt_type >", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("opt_type >=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(String value) {
            addCriterion("opt_type <", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(String value) {
            addCriterion("opt_type <=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLike(String value) {
            addCriterion("opt_type like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotLike(String value) {
            addCriterion("opt_type not like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<String> values) {
            addCriterion("opt_type in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<String> values) {
            addCriterion("opt_type not in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(String value1, String value2) {
            addCriterion("opt_type between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(String value1, String value2) {
            addCriterion("opt_type not between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andGameKindBIsNull() {
            addCriterion("game_kind_B is null");
            return (Criteria) this;
        }

        public Criteria andGameKindBIsNotNull() {
            addCriterion("game_kind_B is not null");
            return (Criteria) this;
        }

        public Criteria andGameKindBEqualTo(String value) {
            addCriterion("game_kind_B =", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBNotEqualTo(String value) {
            addCriterion("game_kind_B <>", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBGreaterThan(String value) {
            addCriterion("game_kind_B >", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBGreaterThanOrEqualTo(String value) {
            addCriterion("game_kind_B >=", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBLessThan(String value) {
            addCriterion("game_kind_B <", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBLessThanOrEqualTo(String value) {
            addCriterion("game_kind_B <=", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBLike(String value) {
            addCriterion("game_kind_B like", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBNotLike(String value) {
            addCriterion("game_kind_B not like", value, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBIn(List<String> values) {
            addCriterion("game_kind_B in", values, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBNotIn(List<String> values) {
            addCriterion("game_kind_B not in", values, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBBetween(String value1, String value2) {
            addCriterion("game_kind_B between", value1, value2, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andGameKindBNotBetween(String value1, String value2) {
            addCriterion("game_kind_B not between", value1, value2, "gameKindB");
            return (Criteria) this;
        }

        public Criteria andOptAmountIsNull() {
            addCriterion("opt_amount is null");
            return (Criteria) this;
        }

        public Criteria andOptAmountIsNotNull() {
            addCriterion("opt_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOptAmountEqualTo(String value) {
            addCriterion("opt_amount =", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountNotEqualTo(String value) {
            addCriterion("opt_amount <>", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountGreaterThan(String value) {
            addCriterion("opt_amount >", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountGreaterThanOrEqualTo(String value) {
            addCriterion("opt_amount >=", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountLessThan(String value) {
            addCriterion("opt_amount <", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountLessThanOrEqualTo(String value) {
            addCriterion("opt_amount <=", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountLike(String value) {
            addCriterion("opt_amount like", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountNotLike(String value) {
            addCriterion("opt_amount not like", value, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountIn(List<String> values) {
            addCriterion("opt_amount in", values, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountNotIn(List<String> values) {
            addCriterion("opt_amount not in", values, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountBetween(String value1, String value2) {
            addCriterion("opt_amount between", value1, value2, "optAmount");
            return (Criteria) this;
        }

        public Criteria andOptAmountNotBetween(String value1, String value2) {
            addCriterion("opt_amount not between", value1, value2, "optAmount");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andStatusLogIsNull() {
            addCriterion("status_log is null");
            return (Criteria) this;
        }

        public Criteria andStatusLogIsNotNull() {
            addCriterion("status_log is not null");
            return (Criteria) this;
        }

        public Criteria andStatusLogEqualTo(String value) {
            addCriterion("status_log =", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogNotEqualTo(String value) {
            addCriterion("status_log <>", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogGreaterThan(String value) {
            addCriterion("status_log >", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogGreaterThanOrEqualTo(String value) {
            addCriterion("status_log >=", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogLessThan(String value) {
            addCriterion("status_log <", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogLessThanOrEqualTo(String value) {
            addCriterion("status_log <=", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogLike(String value) {
            addCriterion("status_log like", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogNotLike(String value) {
            addCriterion("status_log not like", value, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogIn(List<String> values) {
            addCriterion("status_log in", values, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogNotIn(List<String> values) {
            addCriterion("status_log not in", values, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogBetween(String value1, String value2) {
            addCriterion("status_log between", value1, value2, "statusLog");
            return (Criteria) this;
        }

        public Criteria andStatusLogNotBetween(String value1, String value2) {
            addCriterion("status_log not between", value1, value2, "statusLog");
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