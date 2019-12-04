package com.eason.transfer.openapi.user.api.index.swiper.dao.mapper;

import com.eason.transfer.openapi.user.api.index.swiper.dao.entity.TIndexNotice;
import com.eason.transfer.openapi.user.api.index.swiper.dao.entity.TIndexNoticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TIndexNoticeMapper {
    long countByExample(TIndexNoticeExample example);

    int deleteByExample(TIndexNoticeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TIndexNotice record);

    int insertSelective(TIndexNotice record);

    List<TIndexNotice> selectByExample(TIndexNoticeExample example);

    TIndexNotice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TIndexNotice record, @Param("example") TIndexNoticeExample example);

    int updateByExample(@Param("record") TIndexNotice record, @Param("example") TIndexNoticeExample example);

    int updateByPrimaryKeySelective(TIndexNotice record);

    int updateByPrimaryKey(TIndexNotice record);
}