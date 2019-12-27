package com.eason.transfer.openapi.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiAccessLogPo extends BasePo {
    @Id
    private String tid;
    private Integer userId;
    private String invokeMethod;
    private String methodVer;
    private Integer resultType;
    private String resultCode;
    private String resultMsg;
    private String deviceManufacturer;
    private String deviceModel;
    private String systemVer;
    private String appVer;
    private Date visitDate;
    private String ip;
    private String invokeParam;
    private Integer visitTimeCost;
    private String exception;

}
