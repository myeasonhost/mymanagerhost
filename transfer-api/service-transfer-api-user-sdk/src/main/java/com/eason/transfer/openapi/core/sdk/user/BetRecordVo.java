package com.eason.transfer.openapi.core.sdk.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetRecordVo {

    private String gameId;
    private String gameName;
    private String roomName;
    private Double betAmount;
    private Double winAmount;
    private String betTime;

}
