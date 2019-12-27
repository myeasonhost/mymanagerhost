package com.eason.transfer.openapi.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiMethodParamPo extends BasePo {
    @Id
    private String tid;
    private Integer methodId;
    private String paramName;
    private String paramType;
    private Byte isNecessary;
    private String example;
    private String defaultValue;
    private String paramDescription;
    private Byte paramClass;
    private Byte isObject;
    private Date createTime;
}
