package com.eason.transfer.openapi.chess.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TChessGameConfigPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TChessGameConfigPoExample() {
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

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(String value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(String value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(String value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(String value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(String value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(String value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLike(String value) {
            addCriterion("agent_id like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotLike(String value) {
            addCriterion("agent_id not like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<String> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<String> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(String value1, String value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(String value1, String value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
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

        public Criteria andGameKindNameIsNull() {
            addCriterion("game_kind_name is null");
            return (Criteria) this;
        }

        public Criteria andGameKindNameIsNotNull() {
            addCriterion("game_kind_name is not null");
            return (Criteria) this;
        }

        public Criteria andGameKindNameEqualTo(String value) {
            addCriterion("game_kind_name =", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameNotEqualTo(String value) {
            addCriterion("game_kind_name <>", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameGreaterThan(String value) {
            addCriterion("game_kind_name >", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameGreaterThanOrEqualTo(String value) {
            addCriterion("game_kind_name >=", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameLessThan(String value) {
            addCriterion("game_kind_name <", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameLessThanOrEqualTo(String value) {
            addCriterion("game_kind_name <=", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameLike(String value) {
            addCriterion("game_kind_name like", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameNotLike(String value) {
            addCriterion("game_kind_name not like", value, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameIn(List<String> values) {
            addCriterion("game_kind_name in", values, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameNotIn(List<String> values) {
            addCriterion("game_kind_name not in", values, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameBetween(String value1, String value2) {
            addCriterion("game_kind_name between", value1, value2, "gameKindName");
            return (Criteria) this;
        }

        public Criteria andGameKindNameNotBetween(String value1, String value2) {
            addCriterion("game_kind_name not between", value1, value2, "gameKindName");
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

        public Criteria andDeskeyIsNull() {
            addCriterion("DesKey is null");
            return (Criteria) this;
        }

        public Criteria andDeskeyIsNotNull() {
            addCriterion("DesKey is not null");
            return (Criteria) this;
        }

        public Criteria andDeskeyEqualTo(String value) {
            addCriterion("DesKey =", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyNotEqualTo(String value) {
            addCriterion("DesKey <>", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyGreaterThan(String value) {
            addCriterion("DesKey >", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyGreaterThanOrEqualTo(String value) {
            addCriterion("DesKey >=", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyLessThan(String value) {
            addCriterion("DesKey <", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyLessThanOrEqualTo(String value) {
            addCriterion("DesKey <=", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyLike(String value) {
            addCriterion("DesKey like", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyNotLike(String value) {
            addCriterion("DesKey not like", value, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyIn(List<String> values) {
            addCriterion("DesKey in", values, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyNotIn(List<String> values) {
            addCriterion("DesKey not in", values, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyBetween(String value1, String value2) {
            addCriterion("DesKey between", value1, value2, "deskey");
            return (Criteria) this;
        }

        public Criteria andDeskeyNotBetween(String value1, String value2) {
            addCriterion("DesKey not between", value1, value2, "deskey");
            return (Criteria) this;
        }

        public Criteria andMd5keyIsNull() {
            addCriterion("Md5Key is null");
            return (Criteria) this;
        }

        public Criteria andMd5keyIsNotNull() {
            addCriterion("Md5Key is not null");
            return (Criteria) this;
        }

        public Criteria andMd5keyEqualTo(String value) {
            addCriterion("Md5Key =", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyNotEqualTo(String value) {
            addCriterion("Md5Key <>", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyGreaterThan(String value) {
            addCriterion("Md5Key >", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyGreaterThanOrEqualTo(String value) {
            addCriterion("Md5Key >=", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyLessThan(String value) {
            addCriterion("Md5Key <", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyLessThanOrEqualTo(String value) {
            addCriterion("Md5Key <=", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyLike(String value) {
            addCriterion("Md5Key like", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyNotLike(String value) {
            addCriterion("Md5Key not like", value, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyIn(List<String> values) {
            addCriterion("Md5Key in", values, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyNotIn(List<String> values) {
            addCriterion("Md5Key not in", values, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyBetween(String value1, String value2) {
            addCriterion("Md5Key between", value1, value2, "md5key");
            return (Criteria) this;
        }

        public Criteria andMd5keyNotBetween(String value1, String value2) {
            addCriterion("Md5Key not between", value1, value2, "md5key");
            return (Criteria) this;
        }

        public Criteria andWalleturlIsNull() {
            addCriterion("walletUrl is null");
            return (Criteria) this;
        }

        public Criteria andWalleturlIsNotNull() {
            addCriterion("walletUrl is not null");
            return (Criteria) this;
        }

        public Criteria andWalleturlEqualTo(String value) {
            addCriterion("walletUrl =", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlNotEqualTo(String value) {
            addCriterion("walletUrl <>", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlGreaterThan(String value) {
            addCriterion("walletUrl >", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlGreaterThanOrEqualTo(String value) {
            addCriterion("walletUrl >=", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlLessThan(String value) {
            addCriterion("walletUrl <", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlLessThanOrEqualTo(String value) {
            addCriterion("walletUrl <=", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlLike(String value) {
            addCriterion("walletUrl like", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlNotLike(String value) {
            addCriterion("walletUrl not like", value, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlIn(List<String> values) {
            addCriterion("walletUrl in", values, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlNotIn(List<String> values) {
            addCriterion("walletUrl not in", values, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlBetween(String value1, String value2) {
            addCriterion("walletUrl between", value1, value2, "walleturl");
            return (Criteria) this;
        }

        public Criteria andWalleturlNotBetween(String value1, String value2) {
            addCriterion("walletUrl not between", value1, value2, "walleturl");
            return (Criteria) this;
        }

        public Criteria andTransferurlIsNull() {
            addCriterion("transferUrl is null");
            return (Criteria) this;
        }

        public Criteria andTransferurlIsNotNull() {
            addCriterion("transferUrl is not null");
            return (Criteria) this;
        }

        public Criteria andTransferurlEqualTo(String value) {
            addCriterion("transferUrl =", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlNotEqualTo(String value) {
            addCriterion("transferUrl <>", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlGreaterThan(String value) {
            addCriterion("transferUrl >", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlGreaterThanOrEqualTo(String value) {
            addCriterion("transferUrl >=", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlLessThan(String value) {
            addCriterion("transferUrl <", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlLessThanOrEqualTo(String value) {
            addCriterion("transferUrl <=", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlLike(String value) {
            addCriterion("transferUrl like", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlNotLike(String value) {
            addCriterion("transferUrl not like", value, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlIn(List<String> values) {
            addCriterion("transferUrl in", values, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlNotIn(List<String> values) {
            addCriterion("transferUrl not in", values, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlBetween(String value1, String value2) {
            addCriterion("transferUrl between", value1, value2, "transferurl");
            return (Criteria) this;
        }

        public Criteria andTransferurlNotBetween(String value1, String value2) {
            addCriterion("transferUrl not between", value1, value2, "transferurl");
            return (Criteria) this;
        }

        public Criteria andPullurlIsNull() {
            addCriterion("pullUrl is null");
            return (Criteria) this;
        }

        public Criteria andPullurlIsNotNull() {
            addCriterion("pullUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPullurlEqualTo(String value) {
            addCriterion("pullUrl =", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlNotEqualTo(String value) {
            addCriterion("pullUrl <>", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlGreaterThan(String value) {
            addCriterion("pullUrl >", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlGreaterThanOrEqualTo(String value) {
            addCriterion("pullUrl >=", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlLessThan(String value) {
            addCriterion("pullUrl <", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlLessThanOrEqualTo(String value) {
            addCriterion("pullUrl <=", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlLike(String value) {
            addCriterion("pullUrl like", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlNotLike(String value) {
            addCriterion("pullUrl not like", value, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlIn(List<String> values) {
            addCriterion("pullUrl in", values, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlNotIn(List<String> values) {
            addCriterion("pullUrl not in", values, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlBetween(String value1, String value2) {
            addCriterion("pullUrl between", value1, value2, "pullurl");
            return (Criteria) this;
        }

        public Criteria andPullurlNotBetween(String value1, String value2) {
            addCriterion("pullUrl not between", value1, value2, "pullurl");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(Byte value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(Byte value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(Byte value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(Byte value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(Byte value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(Byte value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<Byte> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<Byte> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(Byte value1, Byte value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(Byte value1, Byte value2) {
            addCriterion("length not between", value1, value2, "length");
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

        public Criteria andInitstartidIsNull() {
            addCriterion("initStartId is null");
            return (Criteria) this;
        }

        public Criteria andInitstartidIsNotNull() {
            addCriterion("initStartId is not null");
            return (Criteria) this;
        }

        public Criteria andInitstartidEqualTo(Date value) {
            addCriterion("initStartId =", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidNotEqualTo(Date value) {
            addCriterion("initStartId <>", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidGreaterThan(Date value) {
            addCriterion("initStartId >", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidGreaterThanOrEqualTo(Date value) {
            addCriterion("initStartId >=", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidLessThan(Date value) {
            addCriterion("initStartId <", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidLessThanOrEqualTo(Date value) {
            addCriterion("initStartId <=", value, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidIn(List<Date> values) {
            addCriterion("initStartId in", values, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidNotIn(List<Date> values) {
            addCriterion("initStartId not in", values, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidBetween(Date value1, Date value2) {
            addCriterion("initStartId between", value1, value2, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInitstartidNotBetween(Date value1, Date value2) {
            addCriterion("initStartId not between", value1, value2, "initstartid");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
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