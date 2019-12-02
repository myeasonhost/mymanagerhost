package com.eason.transfer.openapi.chess.dao.mapper;

import com.eason.transfer.openapi.chess.dao.entity.TChessGamePo;
import com.eason.transfer.openapi.chess.dao.entity.TChessGamePoExample;
import com.eason.transfer.openapi.core.sdk.user.BetRecordVo;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TChessGamePoMapper {
    long countByExample(TChessGamePoExample example);

    int deleteByExample(TChessGamePoExample example);

    int deleteByPrimaryKey(String gameid);

    int insert(TChessGamePo record);

    int insertSelective(TChessGamePo record);

    List<TChessGamePo> selectByExample(TChessGamePoExample example);

    TChessGamePo selectByPrimaryKey(String gameid);

    int updateByExampleSelective(@Param("record") TChessGamePo record, @Param("example") TChessGamePoExample example);

    int updateByExample(@Param("record") TChessGamePo record, @Param("example") TChessGamePoExample example);

    int updateByPrimaryKeySelective(TChessGamePo record);

    int updateByPrimaryKey(TChessGamePo record);

    Timestamp getMaxTime(String channelId);

    List<BetRecordVo> selectBetRecord(TChessGamePoExample example);

}