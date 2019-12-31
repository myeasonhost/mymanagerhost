package com.eason.transfer.openapi.core.sdk.zb.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("RoomFindAllResponse")
public class RoomFindAllResponse extends Response {
    private static final long serialVersionUID = 1L;

    private List<RoomVo> list;
}
