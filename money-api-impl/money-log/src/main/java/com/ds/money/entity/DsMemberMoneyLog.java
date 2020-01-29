package com.ds.money.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DsMemberMoneyLog
{
  private Long id;
  private String username;
  private String hashcode;
  private Integer siteId;
  private String sitename;
  private String fromKey;
  private String requestIp;
  private String transId;
  private Integer fromKeyType;
  private BigDecimal beforeMoney;
  private BigDecimal remit;
  private BigDecimal afterMoney;
  private String transType;
  private String gameType;
  private String gameId;
  private Date createTime;
  private String memo;
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = (username == null ? null : username.trim());
  }
  
  public String getHashcode()
  {
    return this.hashcode;
  }
  
  public void setHashcode(String hashcode)
  {
    this.hashcode = (hashcode == null ? null : hashcode.trim());
  }
  
  public Integer getSiteId()
  {
    return this.siteId;
  }
  
  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
  }
  
  public String getSitename()
  {
    return this.sitename;
  }
  
  public void setSitename(String sitename)
  {
    this.sitename = (sitename == null ? null : sitename.trim());
  }
  
  public String getFromKey()
  {
    return this.fromKey;
  }
  
  public void setFromKey(String fromKey)
  {
    this.fromKey = (fromKey == null ? null : fromKey.trim());
  }
  
  public String getRequestIp()
  {
    return this.requestIp;
  }
  
  public void setRequestIp(String requestIp)
  {
    this.requestIp = (requestIp == null ? null : requestIp.trim());
  }
  
  public String getTransId()
  {
    return this.transId;
  }
  
  public void setTransId(String transId)
  {
    this.transId = (transId == null ? null : transId.trim());
  }
  
  public Integer getFromKeyType()
  {
    return this.fromKeyType;
  }
  
  public void setFromKeyType(Integer fromKeyType)
  {
    this.fromKeyType = fromKeyType;
  }
  
  public BigDecimal getBeforeMoney()
  {
    return this.beforeMoney;
  }
  
  public void setBeforeMoney(BigDecimal beforeMoney)
  {
    this.beforeMoney = beforeMoney;
  }
  
  public BigDecimal getRemit()
  {
    return this.remit;
  }
  
  public void setRemit(BigDecimal remit)
  {
    this.remit = remit;
  }
  
  public BigDecimal getAfterMoney()
  {
    return this.afterMoney;
  }
  
  public void setAfterMoney(BigDecimal afterMoney)
  {
    this.afterMoney = afterMoney;
  }
  
  public String getTransType()
  {
    return this.transType;
  }
  
  public void setTransType(String transType)
  {
    this.transType = (transType == null ? null : transType.trim());
  }
  
  public String getGameType()
  {
    return this.gameType;
  }
  
  public void setGameType(String gameType)
  {
    this.gameType = (gameType == null ? null : gameType.trim());
  }
  
  public String getGameId()
  {
    return this.gameId;
  }
  
  public void setGameId(String gameId)
  {
    this.gameId = (gameId == null ? null : gameId.trim());
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getMemo()
  {
    return this.memo;
  }
  
  public void setMemo(String memo)
  {
    this.memo = (memo == null ? null : memo.trim());
  }
}
