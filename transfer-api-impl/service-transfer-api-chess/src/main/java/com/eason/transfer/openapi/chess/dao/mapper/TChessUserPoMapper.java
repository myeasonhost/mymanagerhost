package com.eason.transfer.openapi.chess.dao.mapper;

import com.eason.transfer.openapi.chess.dao.entity.TChessUserPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessUserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TChessUserPoMapper {
    long countByExample(TChessUserPoExample example);

    int deleteByExample(TChessUserPoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TChessUserPo record);

    int insertSelective(TChessUserPo record);

    List<TChessUserPo> selectByExample(TChessUserPoExample example);

    TChessUserPo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TChessUserPo record, @Param("example") TChessUserPoExample example);

    int updateByExample(@Param("record") TChessUserPo record, @Param("example") TChessUserPoExample example);

    int updateByPrimaryKeySelective(TChessUserPo record);

    int updateByPrimaryKey(TChessUserPo record);
}