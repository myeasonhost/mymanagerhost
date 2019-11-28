package com.eason.transfer.openapi.core.sdk.user;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ReportUserGameKindResponse")
public class ReportUserGameKindResponse extends Response {

	private static final long serialVersionUID = 1L;
	
	private List<ReportUserGameKindVo> list;

}
