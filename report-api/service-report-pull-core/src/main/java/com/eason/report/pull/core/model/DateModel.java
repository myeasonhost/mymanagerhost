package com.eason.report.pull.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateModel extends Model{
    private String startId;
    private String endId;
    private Map<Integer,String> siteId;
}
