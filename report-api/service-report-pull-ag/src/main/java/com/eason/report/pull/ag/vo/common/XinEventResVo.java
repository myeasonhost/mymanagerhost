package com.eason.report.pull.ag.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XinEventResVo {
    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;
    private String userName;

}