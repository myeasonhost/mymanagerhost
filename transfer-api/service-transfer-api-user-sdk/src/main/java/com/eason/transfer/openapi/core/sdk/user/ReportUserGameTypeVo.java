package com.eason.transfer.openapi.core.sdk.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUserGameTypeVo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String kindName;
	private String typeName;
	private String roomName;
	private Double betCount;
	private Double betAmount;
	private Double validAmount;
	private Double payAmount;
	private String betTime;

}
