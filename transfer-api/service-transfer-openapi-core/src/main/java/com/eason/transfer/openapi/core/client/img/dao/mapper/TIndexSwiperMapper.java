package com.eason.transfer.openapi.core.client.img.dao.mapper;

import java.util.List;

import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiper;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiperExample;
import org.apache.ibatis.annotations.Param;

public interface TIndexSwiperMapper {
    long countByExample(TIndexSwiperExample example);

    int deleteByExample(TIndexSwiperExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TIndexSwiper record);

    int insertSelective(TIndexSwiper record);

    List<TIndexSwiper> selectByExample(TIndexSwiperExample example);

    TIndexSwiper selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TIndexSwiper record, @Param("example") TIndexSwiperExample example);

    int updateByExample(@Param("record") TIndexSwiper record, @Param("example") TIndexSwiperExample example);

    int updateByPrimaryKeySelective(TIndexSwiper record);

    int updateByPrimaryKey(TIndexSwiper record);
}