package com.eason.transfer.openapi.core.client.chess.dao.mapper;

import com.eason.transfer.openapi.core.client.chess.dao.entity.TReportDayUserPo;
import com.eason.transfer.openapi.core.client.chess.dao.entity.TReportDayUserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReportDayUserPoMapper {
    long countByExample(TReportDayUserPoExample example);

    int deleteByExample(TReportDayUserPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReportDayUserPo record);

    int insertSelective(TReportDayUserPo record);

    List<TReportDayUserPo> selectByExample(TReportDayUserPoExample example);

    TReportDayUserPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReportDayUserPo record, @Param("example") TReportDayUserPoExample example);

    int updateByExample(@Param("record") TReportDayUserPo record, @Param("example") TReportDayUserPoExample example);

    int updateByPrimaryKeySelective(TReportDayUserPo record);

    int updateByPrimaryKey(TReportDayUserPo record);
}