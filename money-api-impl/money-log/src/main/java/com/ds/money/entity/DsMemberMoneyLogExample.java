package com.ds.money.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DsMemberMoneyLogExample
{
  protected String orderByClause;
  protected boolean distinct;
  protected List<Criteria> oredCriteria;
  
  public DsMemberMoneyLogExample()
  {
    this.oredCriteria = new ArrayList();
  }
  
  public void setOrderByClause(String orderByClause)
  {
    this.orderByClause = orderByClause;
  }
  
  public String getOrderByClause()
  {
    return this.orderByClause;
  }
  
  public void setDistinct(boolean distinct)
  {
    this.distinct = distinct;
  }
  
  public boolean isDistinct()
  {
    return this.distinct;
  }
  
  public List<Criteria> getOredCriteria()
  {
    return this.oredCriteria;
  }
  
  public void or(Criteria criteria)
  {
    this.oredCriteria.add(criteria);
  }
  
  public Criteria or()
  {
    Criteria criteria = createCriteriaInternal();
    this.oredCriteria.add(criteria);
    return criteria;
  }
  
  public Criteria createCriteria()
  {
    Criteria criteria = createCriteriaInternal();
    if (this.oredCriteria.size() == 0) {
      this.oredCriteria.add(criteria);
    }
    return criteria;
  }
  
  protected Criteria createCriteriaInternal()
  {
    Criteria criteria = new Criteria();
    return criteria;
  }
  
  public void clear()
  {
    this.oredCriteria.clear();
    this.orderByClause = null;
    this.distinct = false;
  }
  
  protected static abstract class GeneratedCriteria
  {
    protected List<DsMemberMoneyLogExample.Criterion> criteria;
    
    protected GeneratedCriteria()
    {
      this.criteria = new ArrayList();
    }
    
    public boolean isValid()
    {
      return this.criteria.size() > 0;
    }
    
    public List<DsMemberMoneyLogExample.Criterion> getAllCriteria()
    {
      return this.criteria;
    }
    
    public List<DsMemberMoneyLogExample.Criterion> getCriteria()
    {
      return this.criteria;
    }
    
    protected void addCriterion(String condition)
    {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      this.criteria.add(new DsMemberMoneyLogExample.Criterion(condition));
    }
    
    protected void addCriterion(String condition, Object value, String property)
    {
      if (value == null) {
        throw new RuntimeException("Value for " + property + 
          " cannot be null");
      }
      this.criteria.add(new DsMemberMoneyLogExample.Criterion(condition, value));
    }
    
    protected void addCriterion(String condition, Object value1, Object value2, String property)
    {
      if ((value1 == null) || (value2 == null)) {
        throw new RuntimeException("Between values for " + property + 
          " cannot be null");
      }
      this.criteria.add(new DsMemberMoneyLogExample.Criterion(condition, value1, value2));
    }
    
    public DsMemberMoneyLogExample.Criteria andIdIsNull()
    {
      addCriterion("id is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdIsNotNull()
    {
      addCriterion("id is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdEqualTo(Long value)
    {
      addCriterion("id =", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdNotEqualTo(Long value)
    {
      addCriterion("id <>", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdGreaterThan(Long value)
    {
      addCriterion("id >", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdGreaterThanOrEqualTo(Long value)
    {
      addCriterion("id >=", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdLessThan(Long value)
    {
      addCriterion("id <", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdLessThanOrEqualTo(Long value)
    {
      addCriterion("id <=", value, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdIn(List<Long> values)
    {
      addCriterion("id in", values, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdNotIn(List<Long> values)
    {
      addCriterion("id not in", values, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdBetween(Long value1, Long value2)
    {
      addCriterion("id between", value1, value2, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andIdNotBetween(Long value1, Long value2)
    {
      addCriterion("id not between", value1, value2, "id");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameIsNull()
    {
      addCriterion("username is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameIsNotNull()
    {
      addCriterion("username is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameEqualTo(String value)
    {
      addCriterion("username =", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameNotEqualTo(String value)
    {
      addCriterion("username <>", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameGreaterThan(String value)
    {
      addCriterion("username >", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameGreaterThanOrEqualTo(String value)
    {
      addCriterion("username >=", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameLessThan(String value)
    {
      addCriterion("username <", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameLessThanOrEqualTo(String value)
    {
      addCriterion("username <=", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameLike(String value)
    {
      addCriterion("username like", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameNotLike(String value)
    {
      addCriterion("username not like", value, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameIn(List<String> values)
    {
      addCriterion("username in", values, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameNotIn(List<String> values)
    {
      addCriterion("username not in", values, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameBetween(String value1, String value2)
    {
      addCriterion("username between", value1, value2, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andUsernameNotBetween(String value1, String value2)
    {
      addCriterion("username not between", value1, value2, "username");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeIsNull()
    {
      addCriterion("hashcode is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeIsNotNull()
    {
      addCriterion("hashcode is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeEqualTo(String value)
    {
      addCriterion("hashcode =", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeNotEqualTo(String value)
    {
      addCriterion("hashcode <>", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeGreaterThan(String value)
    {
      addCriterion("hashcode >", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeGreaterThanOrEqualTo(String value)
    {
      addCriterion("hashcode >=", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeLessThan(String value)
    {
      addCriterion("hashcode <", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeLessThanOrEqualTo(String value)
    {
      addCriterion("hashcode <=", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeLike(String value)
    {
      addCriterion("hashcode like", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeNotLike(String value)
    {
      addCriterion("hashcode not like", value, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeIn(List<String> values)
    {
      addCriterion("hashcode in", values, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeNotIn(List<String> values)
    {
      addCriterion("hashcode not in", values, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeBetween(String value1, String value2)
    {
      addCriterion("hashcode between", value1, value2, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andHashcodeNotBetween(String value1, String value2)
    {
      addCriterion("hashcode not between", value1, value2, "hashcode");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdIsNull()
    {
      addCriterion("site_id is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdIsNotNull()
    {
      addCriterion("site_id is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdEqualTo(Integer value)
    {
      addCriterion("site_id =", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdNotEqualTo(Integer value)
    {
      addCriterion("site_id <>", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdGreaterThan(Integer value)
    {
      addCriterion("site_id >", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("site_id >=", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdLessThan(Integer value)
    {
      addCriterion("site_id <", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdLessThanOrEqualTo(Integer value)
    {
      addCriterion("site_id <=", value, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdIn(List<Integer> values)
    {
      addCriterion("site_id in", values, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdNotIn(List<Integer> values)
    {
      addCriterion("site_id not in", values, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdBetween(Integer value1, Integer value2)
    {
      addCriterion("site_id between", value1, value2, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSiteIdNotBetween(Integer value1, Integer value2)
    {
      addCriterion("site_id not between", value1, value2, "siteId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameIsNull()
    {
      addCriterion("sitename is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameIsNotNull()
    {
      addCriterion("sitename is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameEqualTo(String value)
    {
      addCriterion("sitename =", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameNotEqualTo(String value)
    {
      addCriterion("sitename <>", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameGreaterThan(String value)
    {
      addCriterion("sitename >", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameGreaterThanOrEqualTo(String value)
    {
      addCriterion("sitename >=", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameLessThan(String value)
    {
      addCriterion("sitename <", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameLessThanOrEqualTo(String value)
    {
      addCriterion("sitename <=", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameLike(String value)
    {
      addCriterion("sitename like", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameNotLike(String value)
    {
      addCriterion("sitename not like", value, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameIn(List<String> values)
    {
      addCriterion("sitename in", values, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameNotIn(List<String> values)
    {
      addCriterion("sitename not in", values, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameBetween(String value1, String value2)
    {
      addCriterion("sitename between", value1, value2, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andSitenameNotBetween(String value1, String value2)
    {
      addCriterion("sitename not between", value1, value2, "sitename");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyIsNull()
    {
      addCriterion("from_key is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyIsNotNull()
    {
      addCriterion("from_key is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyEqualTo(String value)
    {
      addCriterion("from_key =", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyNotEqualTo(String value)
    {
      addCriterion("from_key <>", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyGreaterThan(String value)
    {
      addCriterion("from_key >", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyGreaterThanOrEqualTo(String value)
    {
      addCriterion("from_key >=", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyLessThan(String value)
    {
      addCriterion("from_key <", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyLessThanOrEqualTo(String value)
    {
      addCriterion("from_key <=", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyLike(String value)
    {
      addCriterion("from_key like", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyNotLike(String value)
    {
      addCriterion("from_key not like", value, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyIn(List<String> values)
    {
      addCriterion("from_key in", values, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyNotIn(List<String> values)
    {
      addCriterion("from_key not in", values, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyBetween(String value1, String value2)
    {
      addCriterion("from_key between", value1, value2, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyNotBetween(String value1, String value2)
    {
      addCriterion("from_key not between", value1, value2, "fromKey");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpIsNull()
    {
      addCriterion("request_ip is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpIsNotNull()
    {
      addCriterion("request_ip is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpEqualTo(String value)
    {
      addCriterion("request_ip =", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpNotEqualTo(String value)
    {
      addCriterion("request_ip <>", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpGreaterThan(String value)
    {
      addCriterion("request_ip >", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpGreaterThanOrEqualTo(String value)
    {
      addCriterion("request_ip >=", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpLessThan(String value)
    {
      addCriterion("request_ip <", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpLessThanOrEqualTo(String value)
    {
      addCriterion("request_ip <=", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpLike(String value)
    {
      addCriterion("request_ip like", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpNotLike(String value)
    {
      addCriterion("request_ip not like", value, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpIn(List<String> values)
    {
      addCriterion("request_ip in", values, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpNotIn(List<String> values)
    {
      addCriterion("request_ip not in", values, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpBetween(String value1, String value2)
    {
      addCriterion("request_ip between", value1, value2, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRequestIpNotBetween(String value1, String value2)
    {
      addCriterion("request_ip not between", value1, value2, "requestIp");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdIsNull()
    {
      addCriterion("trans_id is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdIsNotNull()
    {
      addCriterion("trans_id is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdEqualTo(String value)
    {
      addCriterion("trans_id =", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdNotEqualTo(String value)
    {
      addCriterion("trans_id <>", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdGreaterThan(String value)
    {
      addCriterion("trans_id >", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("trans_id >=", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdLessThan(String value)
    {
      addCriterion("trans_id <", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdLessThanOrEqualTo(String value)
    {
      addCriterion("trans_id <=", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdLike(String value)
    {
      addCriterion("trans_id like", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdNotLike(String value)
    {
      addCriterion("trans_id not like", value, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdIn(List<String> values)
    {
      addCriterion("trans_id in", values, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdNotIn(List<String> values)
    {
      addCriterion("trans_id not in", values, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdBetween(String value1, String value2)
    {
      addCriterion("trans_id between", value1, value2, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransIdNotBetween(String value1, String value2)
    {
      addCriterion("trans_id not between", value1, value2, "transId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeIsNull()
    {
      addCriterion("from_key_type is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeIsNotNull()
    {
      addCriterion("from_key_type is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeEqualTo(Integer value)
    {
      addCriterion("from_key_type =", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeNotEqualTo(Integer value)
    {
      addCriterion("from_key_type <>", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeGreaterThan(Integer value)
    {
      addCriterion("from_key_type >", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeGreaterThanOrEqualTo(Integer value)
    {
      addCriterion("from_key_type >=", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeLessThan(Integer value)
    {
      addCriterion("from_key_type <", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeLessThanOrEqualTo(Integer value)
    {
      addCriterion("from_key_type <=", value, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeIn(List<Integer> values)
    {
      addCriterion("from_key_type in", values, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeNotIn(List<Integer> values)
    {
      addCriterion("from_key_type not in", values, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeBetween(Integer value1, Integer value2)
    {
      addCriterion("from_key_type between", value1, value2, "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andFromKeyTypeNotBetween(Integer value1, Integer value2)
    {
      addCriterion("from_key_type not between", value1, value2, 
        "fromKeyType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyIsNull()
    {
      addCriterion("before_money is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyIsNotNull()
    {
      addCriterion("before_money is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyEqualTo(BigDecimal value)
    {
      addCriterion("before_money =", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyNotEqualTo(BigDecimal value)
    {
      addCriterion("before_money <>", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyGreaterThan(BigDecimal value)
    {
      addCriterion("before_money >", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyGreaterThanOrEqualTo(BigDecimal value)
    {
      addCriterion("before_money >=", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyLessThan(BigDecimal value)
    {
      addCriterion("before_money <", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyLessThanOrEqualTo(BigDecimal value)
    {
      addCriterion("before_money <=", value, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyIn(List<BigDecimal> values)
    {
      addCriterion("before_money in", values, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyNotIn(List<BigDecimal> values)
    {
      addCriterion("before_money not in", values, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("before_money between", value1, value2, "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andBeforeMoneyNotBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("before_money not between", value1, value2, 
        "beforeMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitIsNull()
    {
      addCriterion("remit is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitIsNotNull()
    {
      addCriterion("remit is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitEqualTo(BigDecimal value)
    {
      addCriterion("remit =", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitNotEqualTo(BigDecimal value)
    {
      addCriterion("remit <>", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitGreaterThan(BigDecimal value)
    {
      addCriterion("remit >", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitGreaterThanOrEqualTo(BigDecimal value)
    {
      addCriterion("remit >=", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitLessThan(BigDecimal value)
    {
      addCriterion("remit <", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitLessThanOrEqualTo(BigDecimal value)
    {
      addCriterion("remit <=", value, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitIn(List<BigDecimal> values)
    {
      addCriterion("remit in", values, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitNotIn(List<BigDecimal> values)
    {
      addCriterion("remit not in", values, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("remit between", value1, value2, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andRemitNotBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("remit not between", value1, value2, "remit");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyIsNull()
    {
      addCriterion("after_money is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyIsNotNull()
    {
      addCriterion("after_money is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyEqualTo(BigDecimal value)
    {
      addCriterion("after_money =", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyNotEqualTo(BigDecimal value)
    {
      addCriterion("after_money <>", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyGreaterThan(BigDecimal value)
    {
      addCriterion("after_money >", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyGreaterThanOrEqualTo(BigDecimal value)
    {
      addCriterion("after_money >=", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyLessThan(BigDecimal value)
    {
      addCriterion("after_money <", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyLessThanOrEqualTo(BigDecimal value)
    {
      addCriterion("after_money <=", value, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyIn(List<BigDecimal> values)
    {
      addCriterion("after_money in", values, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyNotIn(List<BigDecimal> values)
    {
      addCriterion("after_money not in", values, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("after_money between", value1, value2, "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andAfterMoneyNotBetween(BigDecimal value1, BigDecimal value2)
    {
      addCriterion("after_money not between", value1, value2, 
        "afterMoney");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeIsNull()
    {
      addCriterion("trans_type is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeIsNotNull()
    {
      addCriterion("trans_type is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeEqualTo(String value)
    {
      addCriterion("trans_type =", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeNotEqualTo(String value)
    {
      addCriterion("trans_type <>", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeGreaterThan(String value)
    {
      addCriterion("trans_type >", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeGreaterThanOrEqualTo(String value)
    {
      addCriterion("trans_type >=", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeLessThan(String value)
    {
      addCriterion("trans_type <", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeLessThanOrEqualTo(String value)
    {
      addCriterion("trans_type <=", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeLike(String value)
    {
      addCriterion("trans_type like", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeNotLike(String value)
    {
      addCriterion("trans_type not like", value, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeIn(List<String> values)
    {
      addCriterion("trans_type in", values, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeNotIn(List<String> values)
    {
      addCriterion("trans_type not in", values, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeBetween(String value1, String value2)
    {
      addCriterion("trans_type between", value1, value2, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andTransTypeNotBetween(String value1, String value2)
    {
      addCriterion("trans_type not between", value1, value2, "transType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeIsNull()
    {
      addCriterion("game_type is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeIsNotNull()
    {
      addCriterion("game_type is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeEqualTo(String value)
    {
      addCriterion("game_type =", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeNotEqualTo(String value)
    {
      addCriterion("game_type <>", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeGreaterThan(String value)
    {
      addCriterion("game_type >", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeGreaterThanOrEqualTo(String value)
    {
      addCriterion("game_type >=", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeLessThan(String value)
    {
      addCriterion("game_type <", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeLessThanOrEqualTo(String value)
    {
      addCriterion("game_type <=", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeLike(String value)
    {
      addCriterion("game_type like", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeNotLike(String value)
    {
      addCriterion("game_type not like", value, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeIn(List<String> values)
    {
      addCriterion("game_type in", values, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeNotIn(List<String> values)
    {
      addCriterion("game_type not in", values, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeBetween(String value1, String value2)
    {
      addCriterion("game_type between", value1, value2, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameTypeNotBetween(String value1, String value2)
    {
      addCriterion("game_type not between", value1, value2, "gameType");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdIsNull()
    {
      addCriterion("game_id is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdIsNotNull()
    {
      addCriterion("game_id is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdEqualTo(String value)
    {
      addCriterion("game_id =", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdNotEqualTo(String value)
    {
      addCriterion("game_id <>", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdGreaterThan(String value)
    {
      addCriterion("game_id >", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdGreaterThanOrEqualTo(String value)
    {
      addCriterion("game_id >=", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdLessThan(String value)
    {
      addCriterion("game_id <", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdLessThanOrEqualTo(String value)
    {
      addCriterion("game_id <=", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdLike(String value)
    {
      addCriterion("game_id like", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdNotLike(String value)
    {
      addCriterion("game_id not like", value, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdIn(List<String> values)
    {
      addCriterion("game_id in", values, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdNotIn(List<String> values)
    {
      addCriterion("game_id not in", values, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdBetween(String value1, String value2)
    {
      addCriterion("game_id between", value1, value2, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andGameIdNotBetween(String value1, String value2)
    {
      addCriterion("game_id not between", value1, value2, "gameId");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeIsNull()
    {
      addCriterion("create_time is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeIsNotNull()
    {
      addCriterion("create_time is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeEqualTo(Date value)
    {
      addCriterion("create_time =", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeNotEqualTo(Date value)
    {
      addCriterion("create_time <>", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeGreaterThan(Date value)
    {
      addCriterion("create_time >", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeGreaterThanOrEqualTo(Date value)
    {
      addCriterion("create_time >=", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeLessThan(Date value)
    {
      addCriterion("create_time <", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeLessThanOrEqualTo(Date value)
    {
      addCriterion("create_time <=", value, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeIn(List<Date> values)
    {
      addCriterion("create_time in", values, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeNotIn(List<Date> values)
    {
      addCriterion("create_time not in", values, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeBetween(Date value1, Date value2)
    {
      addCriterion("create_time between", value1, value2, "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andCreateTimeNotBetween(Date value1, Date value2)
    {
      addCriterion("create_time not between", value1, value2, 
        "createTime");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoIsNull()
    {
      addCriterion("memo is null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoIsNotNull()
    {
      addCriterion("memo is not null");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoEqualTo(String value)
    {
      addCriterion("memo =", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoNotEqualTo(String value)
    {
      addCriterion("memo <>", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoGreaterThan(String value)
    {
      addCriterion("memo >", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoGreaterThanOrEqualTo(String value)
    {
      addCriterion("memo >=", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoLessThan(String value)
    {
      addCriterion("memo <", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoLessThanOrEqualTo(String value)
    {
      addCriterion("memo <=", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoLike(String value)
    {
      addCriterion("memo like", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoNotLike(String value)
    {
      addCriterion("memo not like", value, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoIn(List<String> values)
    {
      addCriterion("memo in", values, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoNotIn(List<String> values)
    {
      addCriterion("memo not in", values, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoBetween(String value1, String value2)
    {
      addCriterion("memo between", value1, value2, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
    
    public DsMemberMoneyLogExample.Criteria andMemoNotBetween(String value1, String value2)
    {
      addCriterion("memo not between", value1, value2, "memo");
      return (DsMemberMoneyLogExample.Criteria)this;
    }
  }
  
  public static class Criterion
  {
    private String condition;
    private Object value;
    private Object secondValue;
    private boolean noValue;
    private boolean singleValue;
    private boolean betweenValue;
    private boolean listValue;
    private String typeHandler;
    
    public String getCondition()
    {
      return this.condition;
    }
    
    public Object getValue()
    {
      return this.value;
    }
    
    public Object getSecondValue()
    {
      return this.secondValue;
    }
    
    public boolean isNoValue()
    {
      return this.noValue;
    }
    
    public boolean isSingleValue()
    {
      return this.singleValue;
    }
    
    public boolean isBetweenValue()
    {
      return this.betweenValue;
    }
    
    public boolean isListValue()
    {
      return this.listValue;
    }
    
    public String getTypeHandler()
    {
      return this.typeHandler;
    }
    
    protected Criterion(String condition)
    {
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }
    
    protected Criterion(String condition, Object value, String typeHandler)
    {
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if ((value instanceof List)) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }
    
    protected Criterion(String condition, Object value)
    {
      this(condition, value, null);
    }
    
    protected Criterion(String condition, Object value, Object secondValue, String typeHandler)
    {
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }
    
    protected Criterion(String condition, Object value, Object secondValue)
    {
      this(condition, value, secondValue, null);
    }
  }
  
  public static class Criteria
    extends DsMemberMoneyLogExample.GeneratedCriteria
  {}
}
