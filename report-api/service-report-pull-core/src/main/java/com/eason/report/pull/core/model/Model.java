package com.eason.report.pull.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Model implements Serializable {
    private String code;
    private Object startId;
    private Object endId;
}
