package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ChargeInfoModel")
public class ChargeBankModel{

    private Integer chargeMatchId;
    private Integer bankId;
    private String amount;
    private String bankType;
    private String bankName;
    private String bankCard;
    private String userName;
}
