package com.eason.report.pull.platform.sgs.vo;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SgsCsvVo {
	
	@CsvBindByName(column = "ugsbetid")
    private String ugsBetId;

    @CsvBindByName(column = "txid")
    private String txid;

    @CsvBindByName(column = "betid")
    private String betId;

    @CsvBindByName(column = "beton")
    private String betOn;
    
    @CsvBindByName(column = "betclosedon")
    private String betClosedOn;

    @CsvBindByName(column = "betupdatedon")
    private String betUpdatedOn;

    @CsvBindByName(column = "timestamp")
    private String timestamp;

    @CsvBindByName(column = "roundid")
    private String roundid;

    @CsvBindByName(column = "roundstatus")
    private String roundStatus;

    @CsvBindByName(column = "userid")
    private String userid;

    @CsvBindByName(column = "username")
    private String username;

    @CsvBindByName(column = "riskamt")
    private BigDecimal riskamt;

    @CsvBindByName(column = "winamt")
    private BigDecimal winamt;

    @CsvBindByName(column = "winloss")
    private BigDecimal winloss;

    @CsvBindByName(column = "beforebal")
    private BigDecimal beforebal;

    @CsvBindByName(column = "postbal")
    private BigDecimal postbal;

    @CsvBindByName(column = "cur")
    private String currency;

    @CsvBindByName(column = "gameprovider")
    private String gameProvider;

    @CsvBindByName(column = "gameprovidercode")
    private String gameProviderCode;

    @CsvBindByName(column = "gamename")
    private String gameName;

    @CsvBindByName(column = "gameid")
    private String gameId;

    @CsvBindByName(column = "platformtype")
    private String platformType;

    @CsvBindByName(column = "ipaddress")
    private String ipAddress;

    @CsvBindByName(column = "bettype")
    private String betType;

    @CsvBindByName(column = "playtype")
    private String playType;

    @CsvBindByName(column = "playertype")
    private Byte playerType;

    @CsvBindByName(column = "turnover")
    private BigDecimal turnover;

    @CsvBindByName(column = "validbet")
    private BigDecimal validbet;
}
