package com.eason.transfer.openapi.core.client.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyLogRequest extends Request {
    private String username;
    private String remitno;

    private String beginTime;
    private String endTime;
    private String page ;
    private String pageSize;
    private String fromKey;
    private String key;
    private String siteId ;
    private String fromKeyType;
    private String agentLevel;
    private String agentName;

    private String fromKeyTypeIsTotal;// 否是统计现金流
    private String userInfoIsDetail;// 是否查询用户的详细信息
}
