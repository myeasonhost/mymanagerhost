package com.ds.money.dao.mapper;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.TotalLogVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface MoneyLogDao
{
  public abstract void batchInsertLog(@Param("siteId") Integer paramInteger, @Param("list") List<DsMemberMoneyLog> paramList);

  public abstract List<Integer> getSiteList();

  public abstract List<TotalLogVo> gettotalLogFromLog(@Param("tableName") String moneyLogTable);

  public abstract void batchInsertOrUpdateLog(List<TotalLogVo> list);

}
