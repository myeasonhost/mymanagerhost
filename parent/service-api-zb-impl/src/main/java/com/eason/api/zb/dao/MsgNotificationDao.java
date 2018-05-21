package com.eason.api.zb.dao;

import com.eason.api.zb.po.ZbTQvodNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MsgNotificationDao extends JpaRepository<ZbTQvodNews, Integer> {
    @Query(value = "SELECT * FROM qvod_news news WHERE news.status=8 ORDER BY news.rank ASC;",nativeQuery = true)
    List<ZbTQvodNews> getAll();
}
