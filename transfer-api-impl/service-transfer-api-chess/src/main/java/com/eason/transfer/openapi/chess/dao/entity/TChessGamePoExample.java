package com.eason.transfer.openapi.chess.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TChessGamePoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow;

    protected int pageSize;


    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;

    }

    public TChessGamePoExample() {
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

        public Criteria andGameidIsNull() {
            addCriterion("GameID is null");
            return (Criteria) this;
        }

        public Criteria andGameidIsNotNull() {
            addCriterion("GameID is not null");
            return (Criteria) this;
        }

        public Criteria andGameidEqualTo(String value) {
            addCriterion("GameID =", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotEqualTo(String value) {
            addCriterion("GameID <>", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThan(String value) {
            addCriterion("GameID >", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidGreaterThanOrEqualTo(String value) {
            addCriterion("GameID >=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThan(String value) {
            addCriterion("GameID <", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLessThanOrEqualTo(String value) {
            addCriterion("GameID <=", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidLike(String value) {
            addCriterion("GameID like", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotLike(String value) {
            addCriterion("GameID not like", value, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidIn(List<String> values) {
            addCriterion("GameID in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotIn(List<String> values) {
            addCriterion("GameID not in", values, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidBetween(String value1, String value2) {
            addCriterion("GameID between", value1, value2, "gameid");
            return (Criteria) this;
        }

        public Criteria andGameidNotBetween(String value1, String value2) {
            addCriterion("GameID not between", value1, value2, "gameid");
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
            addCriterion("Account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("Account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("Account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("Account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("Account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("Account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("Account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("Account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("Account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("Account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("Account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("Account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("Account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("Account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andServeridIsNull() {
            addCriterion("ServerID is null");
            return (Criteria) this;
        }

        public Criteria andServeridIsNotNull() {
            addCriterion("ServerID is not null");
            return (Criteria) this;
        }

        public Criteria andServeridEqualTo(Integer value) {
            addCriterion("ServerID =", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotEqualTo(Integer value) {
            addCriterion("ServerID <>", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridGreaterThan(Integer value) {
            addCriterion("ServerID >", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("ServerID >=", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridLessThan(Integer value) {
            addCriterion("ServerID <", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridLessThanOrEqualTo(Integer value) {
            addCriterion("ServerID <=", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridIn(List<Integer> values) {
            addCriterion("ServerID in", values, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotIn(List<Integer> values) {
            addCriterion("ServerID not in", values, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridBetween(Integer value1, Integer value2) {
            addCriterion("ServerID between", value1, value2, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotBetween(Integer value1, Integer value2) {
            addCriterion("ServerID not between", value1, value2, "serverid");
            return (Criteria) this;
        }

        public Criteria andKindidIsNull() {
            addCriterion("KindID is null");
            return (Criteria) this;
        }

        public Criteria andKindidIsNotNull() {
            addCriterion("KindID is not null");
            return (Criteria) this;
        }

        public Criteria andKindidEqualTo(Integer value) {
            addCriterion("KindID =", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidNotEqualTo(Integer value) {
            addCriterion("KindID <>", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidGreaterThan(Integer value) {
            addCriterion("KindID >", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidGreaterThanOrEqualTo(Integer value) {
            addCriterion("KindID >=", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidLessThan(Integer value) {
            addCriterion("KindID <", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidLessThanOrEqualTo(Integer value) {
            addCriterion("KindID <=", value, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidIn(List<Integer> values) {
            addCriterion("KindID in", values, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidNotIn(List<Integer> values) {
            addCriterion("KindID not in", values, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidBetween(Integer value1, Integer value2) {
            addCriterion("KindID between", value1, value2, "kindid");
            return (Criteria) this;
        }

        public Criteria andKindidNotBetween(Integer value1, Integer value2) {
            addCriterion("KindID not between", value1, value2, "kindid");
            return (Criteria) this;
        }

        public Criteria andTableidIsNull() {
            addCriterion("TableID is null");
            return (Criteria) this;
        }

        public Criteria andTableidIsNotNull() {
            addCriterion("TableID is not null");
            return (Criteria) this;
        }

        public Criteria andTableidEqualTo(Long value) {
            addCriterion("TableID =", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidNotEqualTo(Long value) {
            addCriterion("TableID <>", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidGreaterThan(Long value) {
            addCriterion("TableID >", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidGreaterThanOrEqualTo(Long value) {
            addCriterion("TableID >=", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidLessThan(Long value) {
            addCriterion("TableID <", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidLessThanOrEqualTo(Long value) {
            addCriterion("TableID <=", value, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidIn(List<Long> values) {
            addCriterion("TableID in", values, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidNotIn(List<Long> values) {
            addCriterion("TableID not in", values, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidBetween(Long value1, Long value2) {
            addCriterion("TableID between", value1, value2, "tableid");
            return (Criteria) this;
        }

        public Criteria andTableidNotBetween(Long value1, Long value2) {
            addCriterion("TableID not between", value1, value2, "tableid");
            return (Criteria) this;
        }

        public Criteria andChairidIsNull() {
            addCriterion("ChairID is null");
            return (Criteria) this;
        }

        public Criteria andChairidIsNotNull() {
            addCriterion("ChairID is not null");
            return (Criteria) this;
        }

        public Criteria andChairidEqualTo(Integer value) {
            addCriterion("ChairID =", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidNotEqualTo(Integer value) {
            addCriterion("ChairID <>", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidGreaterThan(Integer value) {
            addCriterion("ChairID >", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ChairID >=", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidLessThan(Integer value) {
            addCriterion("ChairID <", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidLessThanOrEqualTo(Integer value) {
            addCriterion("ChairID <=", value, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidIn(List<Integer> values) {
            addCriterion("ChairID in", values, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidNotIn(List<Integer> values) {
            addCriterion("ChairID not in", values, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidBetween(Integer value1, Integer value2) {
            addCriterion("ChairID between", value1, value2, "chairid");
            return (Criteria) this;
        }

        public Criteria andChairidNotBetween(Integer value1, Integer value2) {
            addCriterion("ChairID not between", value1, value2, "chairid");
            return (Criteria) this;
        }

        public Criteria andUsercountIsNull() {
            addCriterion("UserCount is null");
            return (Criteria) this;
        }

        public Criteria andUsercountIsNotNull() {
            addCriterion("UserCount is not null");
            return (Criteria) this;
        }

        public Criteria andUsercountEqualTo(Integer value) {
            addCriterion("UserCount =", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountNotEqualTo(Integer value) {
            addCriterion("UserCount <>", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountGreaterThan(Integer value) {
            addCriterion("UserCount >", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserCount >=", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountLessThan(Integer value) {
            addCriterion("UserCount <", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountLessThanOrEqualTo(Integer value) {
            addCriterion("UserCount <=", value, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountIn(List<Integer> values) {
            addCriterion("UserCount in", values, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountNotIn(List<Integer> values) {
            addCriterion("UserCount not in", values, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountBetween(Integer value1, Integer value2) {
            addCriterion("UserCount between", value1, value2, "usercount");
            return (Criteria) this;
        }

        public Criteria andUsercountNotBetween(Integer value1, Integer value2) {
            addCriterion("UserCount not between", value1, value2, "usercount");
            return (Criteria) this;
        }

        public Criteria andCardvalueIsNull() {
            addCriterion("CardValue is null");
            return (Criteria) this;
        }

        public Criteria andCardvalueIsNotNull() {
            addCriterion("CardValue is not null");
            return (Criteria) this;
        }

        public Criteria andCardvalueEqualTo(String value) {
            addCriterion("CardValue =", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueNotEqualTo(String value) {
            addCriterion("CardValue <>", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueGreaterThan(String value) {
            addCriterion("CardValue >", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueGreaterThanOrEqualTo(String value) {
            addCriterion("CardValue >=", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueLessThan(String value) {
            addCriterion("CardValue <", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueLessThanOrEqualTo(String value) {
            addCriterion("CardValue <=", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueLike(String value) {
            addCriterion("CardValue like", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueNotLike(String value) {
            addCriterion("CardValue not like", value, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueIn(List<String> values) {
            addCriterion("CardValue in", values, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueNotIn(List<String> values) {
            addCriterion("CardValue not in", values, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueBetween(String value1, String value2) {
            addCriterion("CardValue between", value1, value2, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCardvalueNotBetween(String value1, String value2) {
            addCriterion("CardValue not between", value1, value2, "cardvalue");
            return (Criteria) this;
        }

        public Criteria andCellscoreIsNull() {
            addCriterion("CellScore is null");
            return (Criteria) this;
        }

        public Criteria andCellscoreIsNotNull() {
            addCriterion("CellScore is not null");
            return (Criteria) this;
        }

        public Criteria andCellscoreEqualTo(BigDecimal value) {
            addCriterion("CellScore =", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreNotEqualTo(BigDecimal value) {
            addCriterion("CellScore <>", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreGreaterThan(BigDecimal value) {
            addCriterion("CellScore >", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CellScore >=", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreLessThan(BigDecimal value) {
            addCriterion("CellScore <", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CellScore <=", value, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreIn(List<BigDecimal> values) {
            addCriterion("CellScore in", values, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreNotIn(List<BigDecimal> values) {
            addCriterion("CellScore not in", values, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CellScore between", value1, value2, "cellscore");
            return (Criteria) this;
        }

        public Criteria andCellscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CellScore not between", value1, value2, "cellscore");
            return (Criteria) this;
        }

        public Criteria andAllbetIsNull() {
            addCriterion("AllBet is null");
            return (Criteria) this;
        }

        public Criteria andAllbetIsNotNull() {
            addCriterion("AllBet is not null");
            return (Criteria) this;
        }

        public Criteria andAllbetEqualTo(BigDecimal value) {
            addCriterion("AllBet =", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetNotEqualTo(BigDecimal value) {
            addCriterion("AllBet <>", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetGreaterThan(BigDecimal value) {
            addCriterion("AllBet >", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AllBet >=", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetLessThan(BigDecimal value) {
            addCriterion("AllBet <", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AllBet <=", value, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetIn(List<BigDecimal> values) {
            addCriterion("AllBet in", values, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetNotIn(List<BigDecimal> values) {
            addCriterion("AllBet not in", values, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AllBet between", value1, value2, "allbet");
            return (Criteria) this;
        }

        public Criteria andAllbetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AllBet not between", value1, value2, "allbet");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("Profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("Profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(BigDecimal value) {
            addCriterion("Profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(BigDecimal value) {
            addCriterion("Profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(BigDecimal value) {
            addCriterion("Profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(BigDecimal value) {
            addCriterion("Profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<BigDecimal> values) {
            addCriterion("Profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<BigDecimal> values) {
            addCriterion("Profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNull() {
            addCriterion("Revenue is null");
            return (Criteria) this;
        }

        public Criteria andRevenueIsNotNull() {
            addCriterion("Revenue is not null");
            return (Criteria) this;
        }

        public Criteria andRevenueEqualTo(BigDecimal value) {
            addCriterion("Revenue =", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotEqualTo(BigDecimal value) {
            addCriterion("Revenue <>", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThan(BigDecimal value) {
            addCriterion("Revenue >", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Revenue >=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThan(BigDecimal value) {
            addCriterion("Revenue <", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Revenue <=", value, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueIn(List<BigDecimal> values) {
            addCriterion("Revenue in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotIn(List<BigDecimal> values) {
            addCriterion("Revenue not in", values, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Revenue between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andRevenueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Revenue not between", value1, value2, "revenue");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIsNull() {
            addCriterion("GameStartTime is null");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIsNotNull() {
            addCriterion("GameStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeEqualTo(Date value) {
            addCriterion("GameStartTime =", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotEqualTo(Date value) {
            addCriterion("GameStartTime <>", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeGreaterThan(Date value) {
            addCriterion("GameStartTime >", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("GameStartTime >=", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeLessThan(Date value) {
            addCriterion("GameStartTime <", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeLessThanOrEqualTo(Date value) {
            addCriterion("GameStartTime <=", value, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeIn(List<Date> values) {
            addCriterion("GameStartTime in", values, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotIn(List<Date> values) {
            addCriterion("GameStartTime not in", values, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeBetween(Date value1, Date value2) {
            addCriterion("GameStartTime between", value1, value2, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGamestarttimeNotBetween(Date value1, Date value2) {
            addCriterion("GameStartTime not between", value1, value2, "gamestarttime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIsNull() {
            addCriterion("GameEndTime is null");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIsNotNull() {
            addCriterion("GameEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andGameendtimeEqualTo(Date value) {
            addCriterion("GameEndTime =", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotEqualTo(Date value) {
            addCriterion("GameEndTime <>", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeGreaterThan(Date value) {
            addCriterion("GameEndTime >", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("GameEndTime >=", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeLessThan(Date value) {
            addCriterion("GameEndTime <", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeLessThanOrEqualTo(Date value) {
            addCriterion("GameEndTime <=", value, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeIn(List<Date> values) {
            addCriterion("GameEndTime in", values, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotIn(List<Date> values) {
            addCriterion("GameEndTime not in", values, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeBetween(Date value1, Date value2) {
            addCriterion("GameEndTime between", value1, value2, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andGameendtimeNotBetween(Date value1, Date value2) {
            addCriterion("GameEndTime not between", value1, value2, "gameendtime");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNull() {
            addCriterion("ChannelID is null");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNotNull() {
            addCriterion("ChannelID is not null");
            return (Criteria) this;
        }

        public Criteria andChannelidEqualTo(Integer value) {
            addCriterion("ChannelID =", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotEqualTo(Integer value) {
            addCriterion("ChannelID <>", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThan(Integer value) {
            addCriterion("ChannelID >", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ChannelID >=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThan(Integer value) {
            addCriterion("ChannelID <", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThanOrEqualTo(Integer value) {
            addCriterion("ChannelID <=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidIn(List<Integer> values) {
            addCriterion("ChannelID in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotIn(List<Integer> values) {
            addCriterion("ChannelID not in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidBetween(Integer value1, Integer value2) {
            addCriterion("ChannelID between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotBetween(Integer value1, Integer value2) {
            addCriterion("ChannelID not between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andLinecodeIsNull() {
            addCriterion("LineCode is null");
            return (Criteria) this;
        }

        public Criteria andLinecodeIsNotNull() {
            addCriterion("LineCode is not null");
            return (Criteria) this;
        }

        public Criteria andLinecodeEqualTo(String value) {
            addCriterion("LineCode =", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeNotEqualTo(String value) {
            addCriterion("LineCode <>", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeGreaterThan(String value) {
            addCriterion("LineCode >", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeGreaterThanOrEqualTo(String value) {
            addCriterion("LineCode >=", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeLessThan(String value) {
            addCriterion("LineCode <", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeLessThanOrEqualTo(String value) {
            addCriterion("LineCode <=", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeLike(String value) {
            addCriterion("LineCode like", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeNotLike(String value) {
            addCriterion("LineCode not like", value, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeIn(List<String> values) {
            addCriterion("LineCode in", values, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeNotIn(List<String> values) {
            addCriterion("LineCode not in", values, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeBetween(String value1, String value2) {
            addCriterion("LineCode between", value1, value2, "linecode");
            return (Criteria) this;
        }

        public Criteria andLinecodeNotBetween(String value1, String value2) {
            addCriterion("LineCode not between", value1, value2, "linecode");
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