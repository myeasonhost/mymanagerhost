package com.ds.money.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserMoneyRequest")
public class UserMoneyRequest extends Request {
    private String username ;
    private String fromKey ;
    private String key;
    private String siteId;
}
