package com.eason.transfer.openapi.chess.dao;


import com.eason.transfer.openapi.chess.po.TLcChessUserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessUserDao extends JpaRepository<TLcChessUserPo, Integer>{



}
