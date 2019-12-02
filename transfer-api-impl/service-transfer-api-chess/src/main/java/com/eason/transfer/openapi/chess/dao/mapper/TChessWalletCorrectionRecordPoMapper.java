package com.eason.transfer.openapi.chess.dao.mapper;

import com.eason.transfer.openapi.chess.dao.entity.TChessWalletCorrectionRecordPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessWalletCorrectionRecordPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TChessWalletCorrectionRecordPoMapper {
    long countByExample(TChessWalletCorrectionRecordPoExample example);

    int deleteByExample(TChessWalletCorrectionRecordPoExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(TChessWalletCorrectionRecordPo record);

    int insertSelective(TChessWalletCorrectionRecordPo record);

    List<TChessWalletCorrectionRecordPo> selectByExample(TChessWalletCorrectionRecordPoExample example);

    TChessWalletCorrectionRecordPo selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") TChessWalletCorrectionRecordPo record, @Param("example") TChessWalletCorrectionRecordPoExample example);

    int updateByExample(@Param("record") TChessWalletCorrectionRecordPo record, @Param("example") TChessWalletCorrectionRecordPoExample example);

    int updateByPrimaryKeySelective(TChessWalletCorrectionRecordPo record);

    int updateByPrimaryKey(TChessWalletCorrectionRecordPo record);
}