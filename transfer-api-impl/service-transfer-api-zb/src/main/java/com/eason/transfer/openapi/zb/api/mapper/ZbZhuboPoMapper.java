package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.zb.api.entity.ZbZhuboPo;
import com.eason.transfer.openapi.zb.api.entity.ZbZhuboPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZbZhuboPoMapper {
    long countByExample(ZbZhuboPoExample example);

    int deleteByExample(ZbZhuboPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ZbZhuboPo record);

    int insertSelective(ZbZhuboPo record);

    List<ZbZhuboPo> selectByExample(ZbZhuboPoExample example);

    ZbZhuboPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ZbZhuboPo record, @Param("example") ZbZhuboPoExample example);

    int updateByExample(@Param("record") ZbZhuboPo record, @Param("example") ZbZhuboPoExample example);

    int updateByPrimaryKeySelective(ZbZhuboPo record);

    int updateByPrimaryKey(ZbZhuboPo record);
}