package com.eason.transfer.openapi.core.sdk.zb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("PullBetBetRecordVo")
public class PullBetBetRecordVo {

    private String gameId;
    private String gameName;
    private String roomName;
    private Double betAmount;
    private Double winAmount;
    private Double revenue;
    private String betTime;

}
