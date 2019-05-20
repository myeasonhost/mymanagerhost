package com.eason.report.pull.ds.vo.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompOrdersVo implements Serializable {

    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;
    private String sessionid;
    private String comptype;


}
