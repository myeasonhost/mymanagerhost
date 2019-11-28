package com.eason.transfer.openapi.core.sdk.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUserGameKindVo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String kindName;
	private Double betCounts;
	private Double betAmounts;
	private Double validAmounts;
	private Double payAmounts;
	private String betTime;

}
