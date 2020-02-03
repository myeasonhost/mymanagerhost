package com.ds.money.vo;

import com.eason.transfer.openapi.core.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyLogResponse extends Response {
    private List list;
    private String result;	//返回信息
    private Integer page;
    private Integer pageSize;
    private long totalNumber;
}
