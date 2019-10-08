package com.eason.transfer.openapi.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Document(collection = "api_method")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiMethodPo extends BasePo {
    @Id
    private String tid;
    private Integer methodType;
    private String method;
    private String methodName;
    private String methodMemo;
    private Integer invokeMinMaxNum;
    private Integer invokeDayMaxNum;
    private Integer authLevel;
    private Integer isUpdated;
    private String xmlResult;
    private String jsonResult;
    private Date createTime;
    private Date updateTime;
    private Integer updateBy;
    private Integer createBy;
    private Byte isExtras;
    private Integer isOpen;
}
