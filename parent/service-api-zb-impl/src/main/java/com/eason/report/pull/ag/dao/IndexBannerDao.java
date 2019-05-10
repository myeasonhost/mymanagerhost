package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.ZbTQvodBanners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndexBannerDao extends JpaRepository<ZbTQvodBanners, Integer> {
    @Query(value = "SELECT * FROM qvod_banners banners WHERE banners.type=? AND banners.status=1 ORDER BY banners.rank ASC",nativeQuery = true)
    List<ZbTQvodBanners> getByType(int type);
}
