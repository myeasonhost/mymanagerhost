package com.eason.transfer.openapi.core.client.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransMoneyRequest extends Request {
    private String username ;
    private String remitno ;//流水号
    private String transType ;//转入转出标识
    private String remit ;
    private String wagerCancel ;//注单是否取消 1：取消    0否
    private String fromKey ;
    private String siteId;
    private String key ;
    private String ip ;
    private String fromKeyType ;
    private String memo;
    private String gameType ;
    private String gameId ;
}
