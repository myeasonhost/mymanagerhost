package com.eason.report.pull.ds.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Map;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DsLotteryModel implements Serializable {
    private String startId;
    private String endId;
    private Map<String,String> siteId;
}
