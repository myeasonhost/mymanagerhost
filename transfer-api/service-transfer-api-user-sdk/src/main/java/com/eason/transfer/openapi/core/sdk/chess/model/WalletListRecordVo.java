package com.eason.transfer.openapi.core.sdk.chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletListRecordVo {

    private String recordId;
    private String account;
    private String gameKindA;
    private String optType;
    private String gameKindB;
    private Double optAmount;
    private Double balance;
    private Integer status;
    private String createTime;

}
