package com.eason.transfer.openapi.core.sdk.chess.model;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUserGameKindRequest extends Request {

	private static final long serialVersionUID = 1L;

	private String username;
	private String siteId;
	private String loginType;
	private String startTime;
	private String endTime;
	private Integer page;
	private Integer pageSize;

}
