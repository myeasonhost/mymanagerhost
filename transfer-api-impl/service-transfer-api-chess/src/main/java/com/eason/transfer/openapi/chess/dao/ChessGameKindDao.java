package com.eason.transfer.openapi.chess.dao;


import com.eason.transfer.openapi.chess.po.TChessGameKindPo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessGameKindDao extends JpaRepository<TChessGameKindPo, Integer>{

    @Cacheable("Cache-Kind")
    TChessGameKindPo findByKingId(Integer gameType);

}
