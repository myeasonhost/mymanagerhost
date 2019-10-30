package com.eason.transfer.openapi.core.client.img.dao.mapper;

import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexNotice;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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