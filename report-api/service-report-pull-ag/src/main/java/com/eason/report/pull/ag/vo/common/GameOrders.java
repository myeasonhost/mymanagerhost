package com.eason.report.pull.ag.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameOrders implements Serializable {

    private String act;
    private String pidtoken;
    private String productid;
    private BigDecimal begintime;
    private BigDecimal endtime;
    private Integer page;
    private String sessionkey;

}
