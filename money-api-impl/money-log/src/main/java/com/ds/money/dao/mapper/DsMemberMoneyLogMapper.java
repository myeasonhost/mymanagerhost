package com.ds.money.dao.mapper;

import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.DsMemberMoneyLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface DsMemberMoneyLogMapper
{
  public abstract int countByExample(DsMemberMoneyLogExample paramDsMemberMoneyLogExample);
  
  public abstract int deleteByExample(DsMemberMoneyLogExample paramDsMemberMoneyLogExample);
  
  public abstract int deleteByPrimaryKey(Long paramLong);
  
  public abstract int insert(DsMemberMoneyLog paramDsMemberMoneyLog);
  
  public abstract int insertSelective(DsMemberMoneyLog paramDsMemberMoneyLog);
  
  public abstract List<DsMemberMoneyLog> selectByExample(DsMemberMoneyLogExample paramDsMemberMoneyLogExample);
  
  public abstract DsMemberMoneyLog selectByPrimaryKey(Long paramLong);
  
  public abstract int updateByExampleSelective(@Param("record") DsMemberMoneyLog paramDsMemberMoneyLog, @Param("example") DsMemberMoneyLogExample paramDsMemberMoneyLogExample);
  
  public abstract int updateByExample(@Param("record") DsMemberMoneyLog paramDsMemberMoneyLog, @Param("example") DsMemberMoneyLogExample paramDsMemberMoneyLogExample);
  
  public abstract int updateByPrimaryKeySelective(DsMemberMoneyLog paramDsMemberMoneyLog);
  
  public abstract int updateByPrimaryKey(DsMemberMoneyLog paramDsMemberMoneyLog);
}
