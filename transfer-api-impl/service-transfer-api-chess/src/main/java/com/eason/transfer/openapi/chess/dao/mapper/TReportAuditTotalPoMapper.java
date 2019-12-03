package com.eason.transfer.openapi.chess.dao.mapper;

import com.eason.transfer.openapi.chess.dao.entity.TReportAuditTotalPo;
import com.eason.transfer.openapi.chess.dao.entity.TReportAuditTotalPoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TReportAuditTotalPoMapper {
    long countByExample(TReportAuditTotalPoExample example);

    int deleteByExample(TReportAuditTotalPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReportAuditTotalPo record);

    int insertSelective(TReportAuditTotalPo record);

    List<TReportAuditTotalPo> selectByExample(TReportAuditTotalPoExample example);

    TReportAuditTotalPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReportAuditTotalPo record, @Param("example") TReportAuditTotalPoExample example);

    int updateByExample(@Param("record") TReportAuditTotalPo record, @Param("example") TReportAuditTotalPoExample example);

    int updateByPrimaryKeySelective(TReportAuditTotalPo record);

    int updateByPrimaryKey(TReportAuditTotalPo record);

    String executeAuditAndReport(@Param("map") Map<String, Object> map);

}