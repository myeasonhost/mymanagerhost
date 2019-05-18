package com.eason.report.pull.h8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoPlayOrdersExVo implements Serializable {
    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;

}
