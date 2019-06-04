package com.eason.report.pull.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasePo implements Serializable {
    private String liveId;
    private String liveName;
    private String parentType;
    private String parentName;
    private String type;
    private String gameName;
}
