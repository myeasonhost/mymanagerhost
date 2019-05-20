package com.eason.report.pull.ds.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumOrdersDaysXinVo implements Serializable {
    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;
}
