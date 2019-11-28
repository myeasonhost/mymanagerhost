package com.eason.transfer.openapi.core.client.chess.dao.mapper;

import com.eason.transfer.openapi.core.client.chess.dao.entity.TChessGameKindPo;
import com.eason.transfer.openapi.core.client.chess.dao.entity.TChessGameKindPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TChessGameKindPoMapper {
    long countByExample(TChessGameKindPoExample example);

    int deleteByExample(TChessGameKindPoExample example);

    int deleteByPrimaryKey(Integer kingid);

    int insert(TChessGameKindPo record);

    int insertSelective(TChessGameKindPo record);

    List<TChessGameKindPo> selectByExample(TChessGameKindPoExample example);

    TChessGameKindPo selectByPrimaryKey(Integer kingid);

    int updateByExampleSelective(@Param("record") TChessGameKindPo record, @Param("example") TChessGameKindPoExample example);

    int updateByExample(@Param("record") TChessGameKindPo record, @Param("example") TChessGameKindPoExample example);

    int updateByPrimaryKeySelective(TChessGameKindPo record);

    int updateByPrimaryKey(TChessGameKindPo record);
}