package com.eason.transfer.openapi.core.sdk.zb.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@XStreamAlias("RelationFriendsRequest")
public class RelationFriendsRequest extends Request {
    private String relationUserId;
}
