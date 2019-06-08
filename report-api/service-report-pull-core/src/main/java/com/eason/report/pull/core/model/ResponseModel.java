package com.eason.report.pull.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel extends Model{
    private String code;
    private String massge;
    private Object obj;
}
