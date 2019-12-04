package com.eason.transfer.openapi.user.api.index.user.dao.mapper;

import com.eason.transfer.openapi.user.api.index.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.user.api.index.user.dao.entity.TUserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserInfoMapper {
    long countByExample(TUserInfoExample example);

    int deleteByExample(TUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserInfo record);

    int insertSelective(TUserInfo record);

    List<TUserInfo> selectByExample(TUserInfoExample example);

    TUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByExample(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByPrimaryKeySelective(TUserInfo record);

    int updateByPrimaryKey(TUserInfo record);
}