package com.eason.transfer.openapi.core.sdk.zb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ReportUserGameKindVo")
public class ReportUserGameKindVo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String gameKind;
	private Integer betCounts;
	private BigDecimal betAmounts;
	private BigDecimal validAmounts;
	private BigDecimal payAmounts;
	private String betTime;

}
