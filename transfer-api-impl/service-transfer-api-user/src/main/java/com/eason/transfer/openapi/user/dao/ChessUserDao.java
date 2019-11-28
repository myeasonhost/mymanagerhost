package com.eason.transfer.openapi.user.dao;


import com.eason.transfer.openapi.user.po.TLcChessUserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessUserDao extends JpaRepository<TLcChessUserPo, Integer>{



}
