package com.eason.transfer.openapi.core.client.chess.dao.mapper;

import com.eason.transfer.openapi.core.client.chess.dao.entity.TChessGameConfigPo;
import com.eason.transfer.openapi.core.client.chess.dao.entity.TChessGameConfigPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TChessGameConfigPoMapper {
    long countByExample(TChessGameConfigPoExample example);

    int deleteByExample(TChessGameConfigPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TChessGameConfigPo record);

    int insertSelective(TChessGameConfigPo record);

    List<TChessGameConfigPo> selectByExample(TChessGameConfigPoExample example);

    TChessGameConfigPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TChessGameConfigPo record, @Param("example") TChessGameConfigPoExample example);

    int updateByExample(@Param("record") TChessGameConfigPo record, @Param("example") TChessGameConfigPoExample example);

    int updateByPrimaryKeySelective(TChessGameConfigPo record);

    int updateByPrimaryKey(TChessGameConfigPo record);
}