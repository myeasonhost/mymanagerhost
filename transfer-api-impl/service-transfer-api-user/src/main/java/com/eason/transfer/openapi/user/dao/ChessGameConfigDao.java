package com.eason.transfer.openapi.user.dao;


import com.eason.transfer.openapi.user.po.TChessGameConfigPo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessGameConfigDao extends JpaRepository<TChessGameConfigPo, Long>{

    @Cacheable("Cache-Config")
    TChessGameConfigPo getTChessGameConfigPoByAndGameKind(String gameKind);

}
