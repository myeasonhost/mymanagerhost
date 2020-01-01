package com.eason.transfer.openapi.core.sdk.zb.model;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateRequest extends Request {
    private static final long serialVersionUID = 1L;

    private String imUrl;
    private String liveUrl;
}
