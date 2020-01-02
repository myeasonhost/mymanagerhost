package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.core.sdk.zb.model.RelationFriendsVo;
import com.eason.transfer.openapi.zb.api.entity.RelationFriendsPo;

import java.util.List;

public interface RelationFriendsMapper {
    List<RelationFriendsVo> selectRelationFriends(String userId);

    void insertRelationFriends(RelationFriendsPo relationFriendsPo);
}
