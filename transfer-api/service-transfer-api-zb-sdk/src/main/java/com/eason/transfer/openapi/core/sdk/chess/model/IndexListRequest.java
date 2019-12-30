package com.eason.transfer.openapi.core.sdk.chess.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ReporttIndexList")
public class IndexListRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String category;
    private Integer position;
    private Integer pageSize;
    private Integer page;
}
