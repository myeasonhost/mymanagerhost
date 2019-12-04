package com.eason.transfer.openapi.core.sdk.chess.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ReportUserGameTypeVo")
public class ReportUserGameTypeVo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String gameKind;
	private String typeName;
	private String roomName;
	private Integer betCount;
	private BigDecimal betAmount;
	private BigDecimal validAmount;
	private BigDecimal payAmount;
	private String betTime;

}
