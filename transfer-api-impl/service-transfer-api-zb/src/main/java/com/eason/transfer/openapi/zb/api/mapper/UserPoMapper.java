package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.zb.api.entity.UserPo;
import com.eason.transfer.openapi.zb.api.entity.UserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPoMapper {
    long countByExample(UserPoExample example);

    int deleteByExample(UserPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    List<UserPo> selectByExample(UserPoExample example);

    UserPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserPo record, @Param("example") UserPoExample example);

    int updateByExample(@Param("record") UserPo record, @Param("example") UserPoExample example);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
}