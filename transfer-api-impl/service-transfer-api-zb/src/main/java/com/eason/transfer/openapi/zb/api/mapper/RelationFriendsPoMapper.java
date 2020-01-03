package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.zb.api.entity.RelationFriendsPo;
import com.eason.transfer.openapi.zb.api.entity.RelationFriendsPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationFriendsPoMapper {
    long countByExample(RelationFriendsPoExample example);

    int deleteByExample(RelationFriendsPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RelationFriendsPo record);

    int insertSelective(RelationFriendsPo record);

    List<RelationFriendsPo> selectByExample(RelationFriendsPoExample example);

    RelationFriendsPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RelationFriendsPo record, @Param("example") RelationFriendsPoExample example);

    int updateByExample(@Param("record") RelationFriendsPo record, @Param("example") RelationFriendsPoExample example);

    int updateByPrimaryKeySelective(RelationFriendsPo record);

    int updateByPrimaryKey(RelationFriendsPo record);
}