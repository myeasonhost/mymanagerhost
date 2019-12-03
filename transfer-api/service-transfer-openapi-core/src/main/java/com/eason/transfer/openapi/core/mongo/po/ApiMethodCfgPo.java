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
public class ApiMethodCfgPo extends BasePo {
    @Id
    private String tid;
    private Integer methodId;
    private String ver;
    private String cfgValue;
    private Byte isDeleted;
    private Date createTime;
    private Date updateTime;
    private Integer createBy;
    private Integer updateBy;

}
