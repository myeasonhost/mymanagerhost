package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.zb.api.entity.ZbRoomReport;
import com.eason.transfer.openapi.zb.api.entity.ZbRoomReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZbRoomReportMapper {
    long countByExample(ZbRoomReportExample example);

    int deleteByExample(ZbRoomReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZbRoomReport record);

    int insertSelective(ZbRoomReport record);

    List<ZbRoomReport> selectByExample(ZbRoomReportExample example);

    ZbRoomReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZbRoomReport record, @Param("example") ZbRoomReportExample example);

    int updateByExample(@Param("record") ZbRoomReport record, @Param("example") ZbRoomReportExample example);

    int updateByPrimaryKeySelective(ZbRoomReport record);

    int updateByPrimaryKey(ZbRoomReport record);
}